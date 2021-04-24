package com.bdefender.statistics;

public enum FileInformation {
    /**
     * contains all information for statistics file.
     */
    STATISTIC_FILE(System.getProperty("user.home") + System.getProperty("file.separator"), "statistics.txt");

    private String completePath;
    private String fileName;
    private static final String SEPARATOR_CHARATTER = "|";

    public String getCompletePath() {
        return this.completePath;
    }

    public String getFileName() {
        return this.fileName;
    }

    public static String getSeparatorCharatter() {
        return SEPARATOR_CHARATTER;
    }


    FileInformation(final String path, final String fileName) {
        this.completePath = path + System.getProperty("file.separator") + fileName;
        this.fileName = fileName;
    }
}
