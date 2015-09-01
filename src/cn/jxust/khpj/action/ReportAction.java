package cn.jxust.khpj.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.jxust.khpj.model.AppraisalReport;
import cn.jxust.khpj.model.BPReport;
import cn.jxust.khpj.model.CDReport;
import cn.jxust.khpj.model.CDReportPrint;
import cn.jxust.khpj.model.MachineAccount;
import cn.jxust.khpj.model.RegisterReport;
import cn.jxust.khpj.service.AppraisalReportService;
import cn.jxust.khpj.service.BPReportService;
import cn.jxust.khpj.service.CDReportService;
import cn.jxust.khpj.service.MachineAccountService;
import cn.jxust.khpj.service.RegisterReportService;
import cn.jxust.orm.PageData;
import cn.jxust.utils.DateUtils;

@Controller
@RequestMapping("/khpj/print_report")
public class ReportAction{

	@Resource
	private CDReportService cdReportService;
	@Resource
	private AppraisalReportService appraisalReportService;
	@Resource
	private BPReportService bPReportService;
	@Resource
	private RegisterReportService registerReportService;
	@Resource
	private MachineAccountService machineAccountService;

	@RequestMapping("/list.php")
	public String list(@RequestParam(defaultValue = "1") int pageNum, String type, Model model)
	{
		PageData<AppraisalReport> ar_pageData = new PageData<AppraisalReport>(1);
		PageData<BPReport> bp_pageData = new PageData<BPReport>(1);
		PageData<RegisterReport> r_pageData = new PageData<RegisterReport>(1);
		if(null == type)
		{
			type = "r";
		}
		if("ar".equals(type))
		{
			ar_pageData = new PageData<AppraisalReport>(pageNum);
		}
		if("bp".equals(type))
		{
			bp_pageData = new PageData<BPReport>(pageNum);
		}
		if("r".equals(type))
		{
			r_pageData = new PageData<RegisterReport>(pageNum);
		}
		model.addAttribute("type", type);
		model.addAttribute("ar", appraisalReportService.getReport(ar_pageData));
		model.addAttribute("bp", bPReportService.getReport(bp_pageData));
		model.addAttribute("r", registerReportService.getReport(r_pageData));
		
		return "/khpj/reports/list";
	}
	
	@RequestMapping("/appraisal_report.php")
	public void createAppraisalReport(HttpServletRequest request, HttpServletResponse response) throws Exception, JRException {
		response.resetBuffer();
		String id = request.getParameter("id");
		AppraisalReport aa = appraisalReportService.getReportById(id);
		String realPath = null;

		response.setContentType("application/vnd.ms-word");
		String fileName = new String("鉴定表.doc".getBytes("GBK"), "ISO8859_1");
		response.setHeader("Content-disposition", "attachment; filename=" + fileName);

		ServletOutputStream ouputStream = response.getOutputStream();
		JRDocxExporter exporter = new JRDocxExporter();
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);

		realPath = request.getSession().getServletContext().getRealPath("//WEB-INF//appraisal_report.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(realPath);
		HashMap<String, Object> parameters = new HashMap<String, Object>();

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanArrayDataSource(new AppraisalReport[]{aa}));
		exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.exportReport();

		ouputStream.flush();
		ouputStream.close();
	}

	@RequestMapping("/bp_report.php")
	public void createBPReport(HttpServletRequest request, HttpServletResponse response) throws Exception, JRException {
		response.reset();  
		List<BPReport> result = new ArrayList<BPReport>();
		String id = request.getParameter("id");
		String realPath = null;
		BPReport bpReport = bPReportService.getReportById(id);
		result.add(bpReport);

		response.setContentType("application/vnd.ms-word");
		String fileName = new String("奖罚表.doc".getBytes("GBK"), "ISO8859_1");
		response.setHeader("Content-disposition", "attachment; filename=" + fileName);

		ServletOutputStream ouputStream = response.getOutputStream();
		JRDocxExporter exporter = new JRDocxExporter();
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);

		HttpSession session = request.getSession();
		if (bpReport.getType().equals("0")) {
			realPath = session.getServletContext().getRealPath(
					"//WEB-INF//b_report.jrxml");
		}else{
			realPath = session.getServletContext().getRealPath(
			"//WEB-INF//p_report.jrxml");
		}
		JasperReport jasperReport = JasperCompileManager.compileReport(realPath);
		HashMap<String, Object> parameters = new HashMap<String, Object>();

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(result, false));
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.exportReport();

		ouputStream.flush();
		ouputStream.close();

	}

	@RequestMapping("/cd_report.php")
	public void createCDReport(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException,
			Exception {
		response.reset();
		String month = request.getParameter("month");
		if(null == month || "".equals(month.trim()))
		{
			month = DateUtils.getDaysForSubmit()[0];
		}
		
		CDReportPrint p = new CDReportPrint();
		Map<Integer, CDReport> result = cdReportService.getReport(month);
		p.setCdMap(result);
		month = month.replace("-", " 年 ");
		p.setMonth(month);
		
		String realPath = null;
		response.setContentType("application/vnd.ms-word");
		String fileName = new String("矛盾纠纷表.doc".getBytes("GBK"), "ISO8859_1");
		response.setHeader("Content-disposition", "attachment; filename=" + fileName);

		ServletOutputStream ouputStream = response.getOutputStream();
		JRDocxExporter exporter = new JRDocxExporter();
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);

		HttpSession session = request.getSession();
		realPath = session.getServletContext().getRealPath("//WEB-INF//cd_report.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(realPath);
		HashMap<String, Object> parameters = new HashMap<String, Object>();

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanArrayDataSource(new CDReportPrint[]{p}));
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.exportReport();

		ouputStream.flush();
		ouputStream.close();
		
	}

	@RequestMapping("/register_report.php")
	public void createRegisterReport(HttpServletRequest request, HttpServletResponse response) throws Exception, JRException {
		response.reset(); 
        String id = request.getParameter("id");  
		List<RegisterReport> result = new ArrayList<RegisterReport>();
		RegisterReport registerReport = registerReportService.getReportById(id);
		result.add(registerReport);
		String realPath = null;
		response.setContentType("application/vnd.ms-word");
		String fileName = new String("登记表.doc".getBytes("GBK"), "ISO8859_1");
		response.setHeader("Content-disposition", "attachment; filename=" + fileName);

		ServletOutputStream ouputStream = response.getOutputStream();
		JRDocxExporter exporter = new JRDocxExporter();
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);

		HttpSession session = request.getSession();
		realPath = session.getServletContext().getRealPath("//WEB-INF//register_report.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(realPath);
		HashMap<String, Object> parameters = new HashMap<String, Object>();

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(result, false));
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.exportReport();

		ouputStream.flush();
		ouputStream.close();

	}

	@RequestMapping("/machine_account.php")
	public void createMachineAccount(HttpServletRequest request, HttpServletResponse response) throws Exception, JRException {
		response.reset(); 
        String month = request.getParameter("month");  
		List<MachineAccount> result = machineAccountService.findMachineAccountByMonth(month);
		String realPath = null;
		response.setContentType("application/vnd.ms-word");
		String fileName = new String("突出矛盾纠纷台账.docx".getBytes("GBK"), "ISO8859_1");
		response.setHeader("Content-disposition", "attachment; filename=" + fileName);

		ServletOutputStream ouputStream = response.getOutputStream();
		JRDocxExporter exporter = new JRDocxExporter();
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);

		HttpSession session = request.getSession();
		realPath = session.getServletContext().getRealPath("//WEB-INF//machine_account.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(realPath);
		HashMap<String, Object> parameters = new HashMap<String, Object>();

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(result, false));
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.exportReport();

		ouputStream.flush();
		ouputStream.close();

	}

}
