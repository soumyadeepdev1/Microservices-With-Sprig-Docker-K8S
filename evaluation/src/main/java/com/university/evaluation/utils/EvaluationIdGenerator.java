package com.university.evaluation.utils;

public class EvaluationIdGenerator {
    public static String generateEvaluationId(String paperId,String enrollmentNo,int attemptNo){
        return  paperId
                +"_"+
                enrollmentNo
                        .substring(
                                enrollmentNo.length() - 4
                        )+
                "_"
                + attemptNo ;
    }
}
