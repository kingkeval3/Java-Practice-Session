package com.java.demo.util;


import com.java.demo.pojos.CellPojo;
import com.java.demo.pojos.MergedCellPojo;
import com.java.demo.pojos.XlsResponsePojo;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class ExcelUtil
{
    public static List<XlsResponsePojo> fetchDataFromXlsDocument(File file) throws Exception {
        try {
            //get the workbook
            FileInputStream excelFile = new FileInputStream(new File(file.getPath()));
            XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
            List <XlsResponsePojo> xlsResponseBeans = new ArrayList<>();
            DataFormatter dataFormatter = new DataFormatter();
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Map <Integer, List <CellPojo>> cellsInEachRowMap = new HashMap <>();
                List <MergedCellPojo> mergedCells = new ArrayList <>();
                List <CellPojo> cells = new ArrayList <>();
                XlsResponsePojo xlsResponseBean = new XlsResponsePojo();
                Map <Integer, Double> rowWidth = new HashMap <>();
                XSSFSheet sheet = workbook.getSheetAt(i);
                Iterator <Row> iterator = sheet.iterator();
                while (iterator.hasNext()) {
                    cells = new ArrayList <>();
                    Row currentRow = iterator.next();
                    rowWidth.put(currentRow.getRowNum(), Double.valueOf(currentRow.getHeight()));
                    for (int j = 0; j < currentRow.getLastCellNum(); j++) {
                        Cell currentCell = currentRow.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        mergedCells = findMergedCells(workbook.getSheetAt(i), mergedCells, currentCell);
                        CellPojo cell = new CellPojo();
                        switch (currentCell.getCellType()) {
                            case STRING:
                                cell.setId(currentCell.getColumnIndex());
                                cell.setValue(currentCell.getRichStringCellValue().toString());
                                break;
                            case NUMERIC:
                                cell.setId(currentCell.getColumnIndex());
                                cell.setValue(dataFormatter.formatCellValue(currentCell));
                                break;
                            case FORMULA:
                                cell.setId(currentCell.getColumnIndex());
                                cell.setValue(String.valueOf(evaluator.evaluateInCell(currentCell)));
                                break;
                            case BLANK:
                                cell.setId(currentCell.getColumnIndex());
                                cell.setValue(null);
                                break;
                        }
                        cells.add(cell);
                    }
                    if(!NullEmptyUtils.isNullorEmpty(cells))
                    {
                        cellsInEachRowMap.put(currentRow.getRowNum(), cells);
                    }
                }
                xlsResponseBean.setCells(cellsInEachRowMap);
                xlsResponseBean.setMergedCells(mergedCells);
                xlsResponseBean.setSheetName(sheet.getSheetName());
                xlsResponseBean.setTable(findAllTablesInSheet(sheet, new ArrayList <>()));
                xlsResponseBean.setColumnWidth(findColumnWidth(sheet));
                xlsResponseBean.setRowWidth(rowWidth);
                xlsResponseBeans.add(xlsResponseBean);
            }
            return xlsResponseBeans;
        } catch (Exception e) {
            throw e;
        }
    }

    private static List <MergedCellPojo> findMergedCells(XSSFSheet sheet, List <MergedCellPojo> mergedCells, Cell currentCell) {
        for (int m = 0; m < sheet.getNumMergedRegions(); m++) {
            CellRangeAddress region = sheet.getMergedRegion(m); //Region of merged cells
            int colIndex = region.getFirstColumn(); //number of columns merged
            int rowNum = region.getFirstRow();      //number of rows merged
            if (rowNum == currentCell.getRowIndex() && colIndex == currentCell.getColumnIndex()) {
                MergedCellPojo mergedCellBean = new MergedCellPojo();
                mergedCellBean.setFirstRow(region.getFirstRow());
                mergedCellBean.setLastRow(region.getLastRow());
                mergedCellBean.setFirstColumn(region.getFirstColumn());
                mergedCellBean.setLastColumn(region.getLastColumn());
                mergedCells.add(mergedCellBean);
            }
        }
        return mergedCells;
    }

    private static List <MergedCellPojo> findAllTablesInSheet(XSSFSheet sheet, List <MergedCellPojo> tablesList) {
        sheet.getTables().stream().forEach(t -> {
            MergedCellPojo table = new MergedCellPojo();
            table.setFirstRow(t.getStartCellReference().getRow());
            table.setLastRow(t.getEndCellReference().getRow());
            table.setFirstColumn(Integer.valueOf(t.getStartCellReference().getCol()));
            table.setLastColumn(Integer.valueOf(t.getEndCellReference().getCol()));
            tablesList.add(table);
        });
        return tablesList;
    }

    private static Map <Integer, Double> findColumnWidth(XSSFSheet sheet) {
        Map <Integer, Double> columnWidth = new HashMap <>();
        for (int i = 0; i < sheet.iterator().next().getPhysicalNumberOfCells(); i++)
            columnWidth.put(i, Double.valueOf(sheet.getColumnWidth(i)));
        return columnWidth;
    }

    public static int getColumnIndices(Row header, FormulaEvaluator formulaEvaluator,
                                       Set expectedHeaders){
        for(int columnIndex = 0 ; columnIndex < expectedHeaders.size() ; columnIndex++){
            CellPojo cellPojo = getCellPojo(header.getCell(columnIndex),formulaEvaluator);
            if(NullEmptyUtils.isNull(cellPojo.getValue()) ||
                    !expectedHeaders.contains(cellPojo.getValue().trim().toLowerCase())){
                return -1;
            }
        }
        return 0;
    }

    public static CellPojo getCellPojo(Cell currentCell, FormulaEvaluator evaluator){
        DataFormatter dataFormatter = new DataFormatter();
        CellPojo cellPojo = new CellPojo();
        if(!NullEmptyUtils.isNull(currentCell))
        {
            switch (currentCell.getCellType())
            {
                case STRING:
                    cellPojo.setId(currentCell.getColumnIndex());
                    cellPojo.setValue(currentCell.getRichStringCellValue().toString().trim());
                    break;
                case NUMERIC:
                    cellPojo.setId(currentCell.getColumnIndex());
                    if (DateUtil.isCellDateFormatted(currentCell))
                        cellPojo.setValue(
                                new DateTime(currentCell.getDateCellValue()).toString(StringConstants.DATE_PATTERN));
                    else
                        cellPojo.setValue(dataFormatter.formatCellValue(currentCell));
                    break;
                case FORMULA:
                    cellPojo.setId(currentCell.getColumnIndex());
                    cellPojo.setValue(String.valueOf(evaluator.evaluateInCell(currentCell)));
                    break;
                case BLANK:
                    cellPojo.setId(currentCell.getColumnIndex());
                    cellPojo.setValue(null);
                    break;
            }
        }
        return cellPojo;
    }

    public static HashMap<String, Integer> getHeaderIndices(Row header, FormulaEvaluator formulaEvaluator){
        Iterator<Cell> cellIterator = header.cellIterator();
        HashMap<String,Integer> headerTitleToIndexMap = new HashMap<>();
        while(cellIterator.hasNext()){
            Cell currentCell = cellIterator.next();
            headerTitleToIndexMap.put(getCellPojo(currentCell, formulaEvaluator).getValue(), currentCell.getColumnIndex());
        }
        return headerTitleToIndexMap;
    }

    public static boolean checkHeader(Iterator<Row> rowIterator, FormulaEvaluator formulaEvaluator,
                                      String[] expectedHeaders) {
        if(rowIterator.hasNext()){
            Row header = rowIterator.next();
            String[] actualHeaders = new String[expectedHeaders.length];
            for (int columnIndex = 0; columnIndex < expectedHeaders.length; columnIndex++) {
                Cell currentCell = header.getCell(columnIndex);
                if(NullEmptyUtils.isNull(currentCell)){
                    return false;
                }
                actualHeaders[columnIndex] = getCellPojo(currentCell, formulaEvaluator).getValue().trim();
            }
            return Arrays.compare(expectedHeaders, actualHeaders) == 0 ? true : false;
        }
        return false;
    }
}