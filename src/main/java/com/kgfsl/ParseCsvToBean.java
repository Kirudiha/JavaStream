package com.kgfsl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

public class ParseCsvToBean {

	public static <T> List<T> convert(final String filename, final char fieldDelimiter, final Class<T> beanClass,
			final String[] columns) throws FileNotFoundException {
		CSVReader reader = null;
		final CsvToBean<T> csv = new CsvToBean<T>();
		try {
			reader = new CSVReader(new BufferedReader(new FileReader(filename)), fieldDelimiter);
			// final HeaderColumnNameMappingStrategy<T> strategy = new
			// HeaderColumnNameMappingStrategy<T>();
			ColumnPositionMappingStrategy<T> strategy = new ColumnPositionMappingStrategy<T>();
			strategy.setType(beanClass);
			strategy.setColumnMapping(columns);

			return csv.parse(strategy, reader);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (final IOException e) {
					// ignore
				}
			}
		}
	}

}
