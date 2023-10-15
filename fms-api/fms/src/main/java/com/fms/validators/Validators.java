package com.fms.validators;


import java.util.Arrays;
import java.util.List;

public class Validators {
    public final static List<AcceptedFileTypes> enumConstantsList = Arrays.asList(AcceptedFileTypes.class.getEnumConstants());

    public final static List<String> acceptedFileTypesList = enumConstantsList.stream().map(element -> element.label).toList();
    public final static String emailValidationRegex="^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public final static String validPasswordRegex="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{12,}$";


    public static Boolean validFileType(String fileType){
        return acceptedFileTypesList.contains(fileType);
    }
    public static Boolean validEmailAddress(String email){
        return email.matches(emailValidationRegex);
    }
    public static Boolean validPassword(String password){
        return password.matches(validPasswordRegex);
    }
}
