package com.liferay.code.samples.portal.modules.applications.portlets.resource_command.command;

import com.liferay.code.samples.portal.modules.applications.portlets.resource_command.model.PersonalTask;
import com.liferay.code.samples.portal.modules.applications.portlets.resource_command.portlet.ResourceCommandPortlet;
import com.liferay.code.samples.portal.modules.applications.portlets.resource_command.service.TaskListService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ContentTypes;
import org.apache.poi.xssf.usermodel.*;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + ResourceCommandPortlet.RESOURCE_COMMAND_PORTLET_NAME,
                "mvc.command.name=/download/tasklist"
        },
        service = MVCResourceCommand.class
)
public class TaskListFileMVCResourceCommand implements MVCResourceCommand {


    @Override
    public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
            throws  PortletException {
        Collection<PersonalTask> personalTasks = taskListService.loadPersonalTasks();

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet sheet = xssfWorkbook.createSheet();

        writeRow(sheet, "Priority", "Task", "Due Date");
        personalTasks.forEach(personalTask -> writeRow(sheet, personalTask));

        try {
            encodeWorkbookIntoResponse(resourceRequest, resourceResponse, xssfWorkbook);
        } catch (IOException ioEx) {
            LOGGER.error(ioEx);
            return false;
        }
        return true;
    }


    private void encodeWorkbookIntoResponse(ResourceRequest resourceRequest,
                                            ResourceResponse resourceResponse,
                                            XSSFWorkbook xssfWorkbook)
            throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        xssfWorkbook.write(baos);
        xssfWorkbook.close();
        baos.flush();
        PortletResponseUtil.sendFile(resourceRequest, resourceResponse,
                FILE_NAME, baos.toByteArray(), ContentTypes.APPLICATION_VND_MS_EXCEL);
    }

    private XSSFRow writeRow(XSSFSheet sheet, PersonalTask personalTask) {
        return writeRow(sheet,
                personalTask.getPriority().toString(),
                personalTask.getTitle(),
                personalTask.getDueDate().format(DateTimeFormatter.ISO_DATE));
    }

    private XSSFRow writeRow(XSSFSheet sheet, String ... values) {
        XSSFRow row =  sheet.createRow(sheet.getLastRowNum() + 1);
        for (int columnIdx = 0; columnIdx < values.length; columnIdx++) {
            row.createCell(columnIdx).setCellValue(values[columnIdx]);
        }
        return row;
    }


    @Reference
    private TaskListService taskListService;

    private static Log LOGGER = LogFactoryUtil.getLog(TaskListFileMVCResourceCommand.class);
    private static final String FILE_NAME = "tasklist.xls";

}
