/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Farhan Fadila
 */
public class Utils {
     final Locale locale = new Locale("id", "ID");
     final NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
    
     
     
     public String formatToRupiah(Number value) {
         nf.setMinimumFractionDigits(0);
         return nf.format(value);
     }
     
    
    public Date toDate(long epochTime) {
        return new Date(epochTime);
    }
    
    
    public Date dateTimeNow () {
        return new Date();
    }
    
    public long epochTimeNow() {
        return Instant.now().toEpochMilli();
    }
    
    
    public long toEpoch(Date date) {
        return date.toInstant().toEpochMilli();
    }
    
    public String dMY(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(date);
    }
    
    public Date stringToDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        return date;
    }
    
    public int diffrenceInDays(Date startDate, Date endDate) {
       long diffInMilliseconds = endDate.getTime() - startDate.getTime();
       
       long daysDiff = TimeUnit.MILLISECONDS.toDays(diffInMilliseconds);
       
       return (int) daysDiff;
    }
    
    public long calculateFine(int days) {
        if(days > 31) {
            return 100000;
        } else if (days >= 21) {
            return 50000;
        } else {
            return 0;
        }
    }

//    Ini buat dapet hari pertama bulan dari date di property
//    Misalkan hari pertama dari bulan juni 2022
//    hari pertamanya 1 Juni 2022 00:00:00
//    Dikemabliin dalam bentuk epoch `1656608399000`
    public long dayStart(Date date) {
        LocalDate ld = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = ld.getMonthValue();
        int year = ld.getYear(); 
        YearMonth ym =  YearMonth.of(year, month);
        return toEpoch(Date.from(ym.atDay(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }
    
//    Ini buat dapet hari terakhir bulan dari date di property
//    Misalkan hari terakhir dari bulan juni 2022
//    hari terakhirnya 30 Juni 2022 23:59:59
//    Dikemabliin dalam bentuk epoch `1656608399000`
     public long dayEnd(Date date) {
        LocalDate ld = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = ld.getMonthValue();
        int year = ld.getYear();
        YearMonth ym =  YearMonth.of(year, month);
        return toEpoch(Date.from(ym.atEndOfMonth().atStartOfDay(ZoneId.systemDefault()).toInstant().plusSeconds(86399)));
    }
     
    public void bukaLaporan(String namaFile, Connection con) {
        try {
            Map params = new HashMap();
            params.put(JRParameter.REPORT_LOCALE, locale);
            String reportFolder = System.getProperty("user.dir") + "/src/laporan/";
            String reportDirectory = reportFolder + namaFile;
            String reportSource = reportDirectory  + ".jrxml";
            String reportDestination = reportDirectory + ".jasper";
            params.putIfAbsent("REPORTS_DIR", reportFolder);

            JasperReport report = JasperCompileManager.compileReport(reportSource);
            JasperPrint print = JasperFillManager.fillReport(report, params, con);
            JasperExportManager.exportReportToHtmlFile(print, reportDestination);
            JasperViewer viewer = new JasperViewer(print, false, locale);
            viewer.setAlwaysOnTop(true);
            viewer.setVisible(true);
        } catch(JRException ex) {
            System.out.println(ex);
        }
    }

    
    public void bukaLaporan(String namaFile, Connection con, Map externalParams) {
        try {
            Map params = new HashMap();
            params.put(JRParameter.REPORT_LOCALE, locale);
           
            params.putAll(externalParams);
            
            String reportFolder = System.getProperty("user.dir") + "/src/laporan/";
            String reportDirectory = reportFolder + namaFile;
            String reportSource = reportDirectory  + ".jrxml";
            String reportDestination = reportDirectory + ".jasper";
            params.putIfAbsent("REPORTS_DIR", reportFolder);

            JasperReport report = JasperCompileManager.compileReport(reportSource);
            JasperPrint print = JasperFillManager.fillReport(report, params, con);
            JasperExportManager.exportReportToHtmlFile(print, reportDestination);
            JasperViewer viewer = new JasperViewer(print, false, locale);
            viewer.setAlwaysOnTop(true);
            viewer.setVisible(true);
        } catch(JRException ex) {
            System.out.println(ex);
        }
    }
    
    public String clearNumber(String value) {
        return value.replaceAll("[^0-9]+", "");
    }
    
    public String mask(String value) {
        final int length = value.length();
        final int endIndex = length / 2;
        String mask = "";
        for(int i = 0; i < length - endIndex; i++) {
            mask += "*";
        }
        return value.substring(0, endIndex) + mask;
    }
    
    public double formatDecimal(double value) {
       DecimalFormat df = new DecimalFormat("0.00");
       return Double.parseDouble(df.format(value));
    }
    
    public int errorDialog(JFrame frame, String message) {
      return  JOptionPane.showConfirmDialog(frame, message, "Error!", JOptionPane.YES_NO_OPTION);
    }
    
    public void successDialog(JFrame frame, String message) {
        JOptionPane.showMessageDialog(frame, message, "Success!", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public String statusToText(String status) {
        switch(status) {
            case "SP":
               return "Dipinjam";
            default:
                return "Dikembalikan";   
        }
    }
    
    public String textToStatus(String text) {
        switch(text) {
            case "Dipinjam":
               return "SP";
            default:
                return "TDK";   
        }
    }
    
    public long denda(long tanggalPinjamInEpoch, int maxPinjam) {
        if(tanggalPinjamInEpoch == 0) {
            return 0;
        }
        
        final long now = epochTimeNow();
        
        final Instant pinjamInstant = Instant.ofEpochMilli(tanggalPinjamInEpoch);
        final Instant nowInstant = Instant.ofEpochMilli(now);
 
        
        final Duration duration = Duration.between(pinjamInstant, nowInstant);
        
        long days = duration.toDays();
        
        if(days <= maxPinjam) {
           return 0;
        }
        
        long maxLong = maxPinjam;
        
        
        return (days - maxLong) * 2000;
    }
    
    public int indexOf(Map<Integer, Integer> map, int id) {
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
          if (entry.getValue().equals(id)) {
            index = entry.getKey();
            return index;
          }
        }
        
        return index;
    }
    
    public boolean isNumber(String text) {
        try {
            Integer.parseInt(text);
            return true; // It is a number
        } catch (NumberFormatException e) {
            return false; // It is not a number
        }
    }
    
    public String generateRandomBarcode(int length) {
        String barcodeChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // Characters to use in the barcode
        StringBuilder barcode = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(barcodeChars.length());
            char randomChar = barcodeChars.charAt(randomIndex);
            barcode.append(randomChar);
        }

        return barcode.toString();
    }
    
    public String convertArray2String(Object[] array) {
         StringBuilder arrayAsStringBuilder = new StringBuilder();
         
         for (int i = 0; i < array.length; i++) {
            if(array[i] == null) break;
            arrayAsStringBuilder.append(array[i]);
            if (i < array.length - 1) {
               if(array[i + 1] != null) {
                    arrayAsStringBuilder.append(",");
               }
            }
         }
         
        
          
         return arrayAsStringBuilder.toString();
    }
}
