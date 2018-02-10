package utils;

import model.entity.Movie;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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
public class ExcelParser implements Constants {
    private final Logger LOGGER = Logger.getLogger(ExcelParser.class);

    public List<Movie> readMoviesFromXLSXFile(int rowsToRead) throws IOException {
        LOGGER.info("Reading movies from excel file");
        List<Movie> movies = new ArrayList<>();

        InputStream ExcelFileToRead = new FileInputStream(PATH_TO_EXCEL);
        XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);

        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row;

        Iterator rows = sheet.rowIterator();
        int rowCounter = 0;

        while (rows.hasNext()) {
            row = (XSSFRow) rows.next();
            if (row.getRowNum() == 0) {
                continue;
            }
            Movie movie = getMovieFromExcel(row);
            if (!movie.getTitle().equals(MOVIE_NOT_FOUND)) {
                movies.add(movie);
            }

            rowCounter++;
            if (rowCounter >= rowsToRead)
                break;
        }
        // deletes last value wich is null
        return movies.stream().filter(message -> !(message.getTitle() == null)).collect(Collectors.toList());
    }

    public Movie findMovieInExcel(String titleCell) throws IOException {
        InputStream ExcelFileToRead = new FileInputStream(PATH_TO_EXCEL);
        XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);

        XSSFSheet sheet = wb.getSheetAt(0);

        for (Row row : sheet) {
            Cell cell = row.getCell(TITLE_INDEX);
            if (cell.getRichStringCellValue().getString().trim().equals(titleCell)) {
                return getMovieFromExcel(row);
            }
        }
        return new Movie(MOVIE_NOT_FOUND, 0.0, 0.0, 0, 0);
    }

    private Movie getMovieFromExcel(Row row) {
        try {
            String title = row.getCell(TITLE_INDEX).getStringCellValue();
            Double myRating = row.getCell(MY_RATING_INDEX).getNumericCellValue();
            Double kinoRating = row.getCell(KINOPOISK_RATING_INDEX).getNumericCellValue();
            Double budget = row.getCell(BUDGET_INDEX).getNumericCellValue();
            Double earnings = row.getCell(EARNINGS_INDEX).getNumericCellValue();
            return new Movie(title, myRating, kinoRating, budget.intValue(), earnings.intValue());
        } catch (RuntimeException e) {
            LOGGER.error(e.getMessage());
            return new Movie(MOVIE_NOT_FOUND, 0.0, 0.0, 0, 0);
        }
    }
}
