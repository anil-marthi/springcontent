package com.wsi.optimize;

import java.util.ArrayList;
import java.util.List;

public class ZipCodeChallenge {

  public List<List<Integer>> optimizeZipCodeRanges(int[][] zipCodeRanges) {
    if(zipCodeRanges==null) return null;

    sortZipCodeRangesBasedOnFirstElem(zipCodeRanges);

    List<List<Integer>> optimizedZipCodeRanges = new ArrayList<>();
    List<Integer> optimizedRange = null;

    for (int i=0; i < zipCodeRanges.length; i++) {

      int[] ithZipCode = zipCodeRanges[i];
      if(ithZipCode==null || ithZipCode.length!=2) return null;

      if(i==0) {

        optimizedRange = new ArrayList<>();
        optimizedRange.add(0, ithZipCode[0]);
        optimizedRange.add(1, ithZipCode[1]);
        optimizedZipCodeRanges.add(optimizedRange);

      } else {

        if(ithZipCode[0]<=optimizedRange.get(1)+1) {

          if(ithZipCode[1]>optimizedRange.get(1)) {
            optimizedRange.set(1, ithZipCode[1]);
          }

        } else {

          optimizedRange = new ArrayList<>();
          optimizedRange.add(0, ithZipCode[0]);
          optimizedRange.add(1, ithZipCode[1]);
          optimizedZipCodeRanges.add(optimizedRange);

        }
      }
    }

    return optimizedZipCodeRanges;
  }

  private int[][] sortZipCodeRangesBasedOnFirstElem(int[][] zipCodeRanges) {

    //Using Insertion sort to sort zip code ranges based on first element on each zip code range
    for(int j=1; j<zipCodeRanges.length; j++) {

      int[] jthZipCodeRange = zipCodeRanges[j];
      int i = j-1;

      while(i>=0 && zipCodeRanges[i][0]> jthZipCodeRange[0]) {
        zipCodeRanges[i+1] = zipCodeRanges[i];
        i = i - 1;
      }

      zipCodeRanges[i+1] = jthZipCodeRange;
    }

    return zipCodeRanges;
  }
}
