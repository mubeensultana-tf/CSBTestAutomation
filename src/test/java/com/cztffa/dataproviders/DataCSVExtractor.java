package com.cztffa.dataproviders;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.cztffa.objects.Person;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class DataCSVExtractor {
	// Java code to illustrate reading a 
	// CSV file line by line 
	
	 	public static List<Map<?, ?>> readObjectsFromCsv(File file) throws IOException {
	        CsvSchema bootstrap = CsvSchema.emptySchema().withHeader();
	        CsvMapper csvMapper = new CsvMapper();
	        try (MappingIterator<Map<?, ?>> mappingIterator = csvMapper.readerFor(Map.class).with(bootstrap).readValues(file)) {
	            return mappingIterator.readAll();
	        }
	    }

	    public static void writeAsJson(List<Map<?, ?>> data, File file) throws IOException {
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.writeValue(file, data);
	    }
	    
	    public static int applicantCount=0;
	    public static int smbapplicationCount=0;
		public static int ownerCount=0;
	    public static boolean isFundingCompulsory=false;
	    public static List<Map<?,?>> applicantDataStore;
	    public static List<Map<?,?>> businessDataStore;
	    public static List<Map<?,?>> businessPersonDataStore;
	    public static List<Map<?,?>> fundingDataStore;
	    public static List<Map<?,?>> smbfundingDataStore;
	    public static List<Map<?,?>> productDataStore;
	    public static List<Map<?,?>> smbproductDataStore;
		public static List<Map<?,?>> salesforceDataStore;

}
