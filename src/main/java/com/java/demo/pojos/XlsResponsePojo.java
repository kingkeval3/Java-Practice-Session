package com.java.demo.pojos;

import java.util.List;
import java.util.Map;

public class XlsResponsePojo {

    private String sheetName;
    private Map <Integer, List <CellPojo>> cells;
    private List <MergedCellPojo> mergedCells;
    private List <MergedCellPojo> table;
    private Map <Integer, Double> columnWidth;
    private Map <Integer, Double> rowWidth;

    /**
     * @return the sheetName
     */
    public String getSheetName() {
        return sheetName;
    }

    /**
     * @param sheetName the sheetName to set
     */
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public Map <Integer, List <CellPojo>> getCells() {
        return cells;
    }

    public void setCells(Map <Integer, List <CellPojo>> cells) {
        this.cells = cells;
    }

    public List <MergedCellPojo> getMergedCells() {
        return mergedCells;
    }

    public void setMergedCells(List <MergedCellPojo> mergedCells) {
        this.mergedCells = mergedCells;
    }

    public List <MergedCellPojo> getTable() {
        return table;
    }

    public void setTable(List <MergedCellPojo> table) {
        this.table = table;
    }

    /**
     * @return the columnWidth
     */
    public Map <Integer, Double> getColumnWidth() {
        return columnWidth;
    }

    /**
     * @param columnWidth the columnWidth to set
     */
    public void setColumnWidth(Map <Integer, Double> columnWidth) {
        this.columnWidth = columnWidth;
    }

    /**
     * @return the rowWidth
     */
    public Map <Integer, Double> getRowWidth() {
        return rowWidth;
    }

    /**
     * @param rowWidth the rowWidth to set
     */
    public void setRowWidth(Map <Integer, Double> rowWidth) {
        this.rowWidth = rowWidth;
    }

}