package com.wsi.optimize;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

import org.apache.log4j.Logger;

import com.wsi.optimize.ZipCodeChallenge;

public class ZipCodeChallengeTest {

  private static Logger logger = Logger.getLogger(ZipCodeChallengeTest.class);
  
  public static void main(String args[]) throws IOException {

    Reader reader = null;
    BufferedReader bf = null;
    PrintWriter pw = null;
    try {
      //User provided input file path 
      reader = new FileReader("in/input.txt");
      bf = new BufferedReader(reader);
      //User provided output file path
      pw = new PrintWriter("out/output.txt");
      String inputLine = "";
      String[] strArr = null;
      String[] innerStrArr = null;
      //the loop reads line by line until end of the file
      while((inputLine = bf.readLine())!=null) {
        StringBuilder sb = new StringBuilder("Merged Result: ");
        logger.debug(inputLine);
        pw.write(inputLine);
        strArr = inputLine.split("\\]\\,\\s?\\[");
        if(strArr!=null) {
          int[][] zipCodeRanges = new int[strArr.length][];
          for (int i=0; i<strArr.length; i++) {
            if(i==0)
              if(strArr[i].length()>2) 
                strArr[0] = strArr[0].substring(2);
              else
                throw new IOException();
            if(i==strArr.length-1)
              if(strArr[i].length()>2)
                strArr[i] = strArr[i].substring(0, strArr[i].length()-2);
              else
                throw new IOException();

            innerStrArr = strArr[i].split("\\,\\s?");
            if(innerStrArr!=null && innerStrArr.length!=2)
              throw new IOException();
            zipCodeRanges[i] = new int[2];
            zipCodeRanges[i][0] = Integer.parseInt(innerStrArr[0]);
            zipCodeRanges[i][1] = Integer.parseInt(innerStrArr[1]);
          }

          ZipCodeChallenge challenge = new ZipCodeChallenge();
          int i=0;
          for (List<Integer> zipCodeRange : challenge.optimizeZipCodeRanges(zipCodeRanges)) {
            if(i>0) sb.append(", ");
            sb.append(zipCodeRange);
            i ++;
          }
        }
        pw.write("\r\n");
        logger.debug(sb.toString());
        pw.write(sb.toString());
        pw.write("\r\n");
        pw.flush();
      }
    } finally {
      //resource cleanup
      if(reader!=null)
        reader.close();
      if(bf!=null)
        bf.close();
      if(pw!=null)
        pw.close();
    }
  }
}
