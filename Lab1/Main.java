import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Main <input_file> <output_file>");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];


        try {
            // Чтение входного XML-файла
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File(inputFile));
            doc.getDocumentElement().normalize();

            // Проверка и коррекция средней оценки
            NodeList students = doc.getElementsByTagName("student");
            for (int i = 0; i < students.getLength(); i++) {
                Node student = students.item(i);
                if (student.getNodeType() == Node.ELEMENT_NODE) {
                    Element studentElement = (Element) student;
                    NodeList marks = studentElement.getElementsByTagName("subject");
                    double totalMarks = 0;
                    int subjectCount = 0;
                    for (int j = 0; j < marks.getLength(); j++) {
                        Element subjectElement = (Element) marks.item(j);
                        int markValue = Integer.parseInt(subjectElement.getAttribute("mark"));
                        totalMarks += markValue;
                        subjectCount++;
                    }
                    double average = totalMarks / subjectCount;
                    String averageStr = String.format("%.1f", average);
                    
                    // Получаем текущее значение средней оценки
                    Node averageNode = studentElement.getElementsByTagName("average").item(0);
                    String currentAverageStr = averageNode.getTextContent().trim();
                    double currentAverage = Double.parseDouble(currentAverageStr);
                    
                    // Сравниваем среднюю оценку и корректируем при необходимости
                    if (Math.abs(currentAverage - average) > 0.1) {
                        averageNode.setTextContent(averageStr);
                    }
                }
            }

            // Запись изменений в выходной XML-файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(outputFile));
            transformer.transform(source, result);

            System.out.println("Average grades corrected and saved to " + outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}