package utils;

import model.entity.Movie;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class ExcelParser {
    public static List<Movie> readMessagesFromXLSXFile(String file, int rowsToRead) throws IOException {
        List<Movie> movies = new ArrayList<>();

        InputStream ExcelFileToRead = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);

        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;

        Iterator rows = sheet.rowIterator();
        int counter = 0;
        int rowCounter = 0;

        String title = "";
        Double myRating = 0.0;
        Double kinopoiskRating = 0.0;
        Double budget = 0.0;
        Double earnings = 0.0;

        while (rows.hasNext()) {
            row = (XSSFRow) rows.next();
            if (row.getRowNum() == 0) {
                continue;
            }
            Iterator cells = row.cellIterator();
            while (cells.hasNext()) {
                cell = (XSSFCell) cells.next();
                    if (counter == 1) {
                        cell.setCellType(CellType.STRING);
                        title = cell.getStringCellValue();
                    } else if(counter == 7){
                        myRating = cell.getNumericCellValue();
                    } else if(counter == 8){
                        kinopoiskRating = cell.getNumericCellValue();
                    } else if(counter == 15) {
                       budget =  cell.getNumericCellValue();
                    } else  if (counter == 17) {
                        earnings = cell.getNumericCellValue();
                    }
                    ++counter;
            }
            counter = 0;
            Movie movie = new Movie(title, myRating, kinopoiskRating, budget.intValue(), earnings.intValue());
            movies.add(movie);
            rowCounter++;
            if (rowCounter >= rowsToRead)
                break;
        }
        // deletes last value wich is null
        return movies.stream().filter(message -> !(message.getTitle() == null)).collect(Collectors.toList());
    }
}
