package com.clinic_project.clinic_system.infra_layer.external.pdf_api;


import com.clinic_project.clinic_system.business_layer.validators.PhoneNumberValidator;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

    public class pdfReader {

        static class PatientRecord {
            private String patientId;
            private String patientName;
            private String patientPhone;
            private String fees;

            public PatientRecord(String patientId, String patientName, String patientPhone, String fees) {
                this.patientId = patientId;
                this.patientName = patientName;
                this.patientPhone = patientPhone;
                this.fees = fees;
            }

            // Getters
            public String getPatientId() {
                return patientId;
            }

            public String getPatientName() {
                return patientName;
            }

            public String getPatientPhone() {
                return patientPhone;
            }

            public String getFees() {
                return fees;
            }

            @Override
            public String toString() {
                return String.format("ID: %-6s | Name: %-30s | Phone: %-12s | Fees: %s",
                        patientId, patientName, patientPhone, fees);
            }
        }

        public static void main(String[] args) {
            String filePath = "C:\\Users\\Sherif\\Downloads\\ProviderProcedure.pdf انا 44.pdf";

            try {
                List<PatientRecord> records = parseDentalPDF(filePath);

                System.out.println("=== PATIENT RECORDS ===");
                System.out.println("Total records found: " + records.size());
                System.out.println();

                // Print header
                System.out.println(String.format("%-6s | %-30s | %-12s | %s",
                        "ID", "Name", "Phone", "Fees"));
                System.out.println("------------------------------------------------------------");

                for (PatientRecord record : records) {
                    System.out.println(record);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static List<PatientRecord> parseDentalPDF(String filePath) throws IOException {
            PDDocument document = Loader.loadPDF(new File(filePath));

            try {
                PDFTextStripper stripper = new PDFTextStripper();
                String pdfText = stripper.getText(document);

                return extractPatientData(pdfText);

            } finally {
                document.close();
            }
        }

        private static List<PatientRecord> extractPatientData(String pdfText) {
            List<PatientRecord> records = new ArrayList<>();

            String[] lines = pdfText.split("Fees|.00\\r?\\n");
            boolean dataStarted = false;

            for (String line : lines) {
                line = line.trim().replaceAll("\\r?\\n", " ");
                //System.out.println(line);

                if (line.isEmpty()) continue;

                // Skip header lines
                if (line.contains("Patient ID") && line.contains("Patient Name")) {
                    dataStarted = true;
                    continue;
                }

                if (dataStarted && !line.contains("Provider Name")) {
                    PatientRecord record = parseLine(line+".00");
                    //System.out.println(line+".00");
                    if (record != null) {
                        records.add(record);

                    }
                }
            }

            return records;
        }

        private static PatientRecord parseLine(String line) {
            try {
                // Extract Patient ID (first 6 digits)
                String patientId = extractPatientId(line);
                if (patientId == null) return null;

                // Extract Phone Number (Egyptian format)
                String phone = extractPhoneNumber(line);
                if (phone.isEmpty()) return null;

                // Extract Fees (last number in the line)
                String fees = extractFees(line);
                if (fees.isEmpty()) return null;

                // Extract Name (between ID and Phone)
                String name = extractPatientName(line);
                //System.out.println(patientId);

                return new PatientRecord(patientId, name, phone, fees);

            } catch (Exception e) {
                System.err.println("Error parsing line: " + line);
                return null;
            }
        }

        private static String extractPatientId(String line) {
            // Look for 6-digit patient ID at the beginning
            Pattern idPattern = Pattern.compile("^(\\d{4,8})");
            Matcher matcher = idPattern.matcher(line);
            if (matcher.find()) {
                return matcher.group(1);
            }
            return null;
        }

        private static String extractPhoneNumber(String line) {
            // Look for Egyptian phone numbers (01xxxxxxxxx)
            Pattern phonePattern = Pattern.compile("01[0-9]{9}");
            Matcher matcher = phonePattern.matcher(line);
            if (matcher.find()) {
                PhoneNumberValidator phonevalidator = new PhoneNumberValidator();
                return phonevalidator.validator(matcher.group());
            }
            return "";
        }

        private static String extractFees(String line) {
            // Look for fee amount (number with optional comma and .00)
            Pattern feesPattern = Pattern.compile("([\\d,]+\\.[\\d]{2})$");
            Matcher matcher = feesPattern.matcher(line);
            if (matcher.find()) {
                return matcher.group(1);
            }
            return "";
        }

        private static String extractPatientName(String line) {
            Pattern namePattern = Pattern.compile("[ء-ي]+");
            Matcher matcher = namePattern.matcher(line);

            // Find all Arabic words and combine them
            StringBuilder name = new StringBuilder();
            while (matcher.find()) {
                name.append(matcher.group()).append(" ");
            }

            return name.toString().trim().replace("الماني", "");

        }
    }