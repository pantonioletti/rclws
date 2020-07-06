package org.pmas.ws;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.pmas.FileProcessing.LoadXlsx;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/analytics")
@MultipartConfig(location = "C:\\dev\\projects\\rclws\\data\\temp")
public class UploadWorkbookServlet extends javax.servlet.http.HttpServlet
{
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
    {
        ServletFileUpload sfu = new ServletFileUpload();

        if (ServletFileUpload.isMultipartContent(req))
        {
            try {
                FileItemIterator fii = sfu.getItemIterator(req);
                while(fii.hasNext()) {
                    FileItemStream item = fii.next();
                    String name = item.getName();
                    if (name.equals("Analysis_01")){
                        LoadXlsx xl01 = new LoadXlsx(item.openStream());
                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("utf-8");
                        PrintWriter w = resp.getWriter();
                        w.print("{\"message\": \"Done!\"}");
                        w.flush();
                    }
                }
            }
            catch(FileUploadException fue){
                fue.printStackTrace();
            }
        }
    }
}
