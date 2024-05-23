package com.saiko.escuela.utils;


import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.saiko.escuela.entity.Grade;

@Configuration
public class AverageGrade {
        @Bean
        public static double calculateAverage(List<Grade> grades){
            double average = 0;
            for (Grade grade : grades){
                average = average + grade.getGrade();
            }
            return average/grades.size();
        }
        
        @Bean
        public static double calculateAverageWithNewGrade(List<Grade> grades, double newGrade){
            double average = 0;
            for (Grade grade : grades){
                average = average + grade.getGrade();
            }
            average = average + newGrade;
            return average/(grades.size()+1);
        }
}
