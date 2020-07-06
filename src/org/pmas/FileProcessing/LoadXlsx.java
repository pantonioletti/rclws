package org.pmas.FileProcessing;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.*;

public class LoadXlsx {
    private Workbook wb = null;

    public LoadXlsx(String pathToFile) throws IOException, FileNotFoundException, SecurityException
    {
        this(new File(pathToFile));
    }

    public LoadXlsx(File xlFile) throws FileNotFoundException, SecurityException, IOException
    {
            this(new FileInputStream(xlFile));

    }

    public LoadXlsx(InputStream xlIs) throws IOException
    {
            this.wb = WorkbookFactory.create(xlIs);
    }

    public String[] getSheetsNames()
    {
        if (wb == null)
            return null;
        String[] retVal = new String[wb.getNumberOfSheets()];
        for (int i=0; i < wb.getNumberOfSheets(); i++){
            retVal[i] = wb.getSheetName(i);
        }
        return retVal;
    }

    public void close() throws IOException
    {
        if (wb != null) {
            wb.close();
            wb = null;
        }
    }

    public static void main(String[] argv) {
        try {
            LoadXlsx xlsx = new LoadXlsx(argv[0]);
            System.out.println(xlsx.getSheetsNames());
            xlsx.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SecurityException se) {
            se.printStackTrace();
        }

    }
}
