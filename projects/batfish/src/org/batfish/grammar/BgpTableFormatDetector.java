package org.batfish.grammar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class BgpTableFormatDetector {

   public static BgpTableFormat identifyBgpTableFormat(String fileText) {
      return new BgpTableFormatDetector(fileText).identifyBgpTableFormat();
   }

   private String _fileText;

   @SuppressWarnings("unused")
   private char _firstChar;

   private BgpTableFormatDetector(String fileText) {
      _fileText = fileText;
   }

   private BgpTableFormat checkEmpty() {
      String trimmedText = _fileText.trim();
      if (trimmedText.length() == 0) {
         return BgpTableFormat.EMPTY;
      }
      _firstChar = trimmedText.charAt(0);
      return null;
   }

   private BgpTableFormat checkEos() {
      Matcher eosMatcher = Pattern
            .compile("(?m)BGP routing table information for VRF")
            .matcher(_fileText);
      if (eosMatcher.find()) {
         return BgpTableFormat.EOS;
      }
      return null;
   }

   private BgpTableFormat identifyBgpTableFormat() {
      BgpTableFormat format;
      format = checkEmpty();
      if (format != null) {
         return format;
      }
      format = checkEos();
      if (format != null) {
         return format;
      }
      return BgpTableFormat.UNKNOWN;
   }

}
