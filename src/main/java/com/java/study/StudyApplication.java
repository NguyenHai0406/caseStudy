package com.java.study;

import com.java.study.common.Constant;
import com.java.study.common.Validator;
import com.java.study.service.MainService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class StudyApplication {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(StudyApplication.class, args);
		MainService mainService = applicationContext.getBean(MainService.class);

		/* Create choice to select your choice in menu */
		int choice = 0;

		String customerCsvPath;
		String emailTemplatePath;
		String outputEmailFolderPath;

		/* This is the menu */
		while (true) {
			System.out.println(Constant.MENU);
			System.out.println(Constant.HYPHEN_TABLE);

			/* Get input your choice */
			choice = Validator.InputNumber(Constant.INPUT_CHOISE, sc);

			/* Handle your choice */
			switch (choice) {

				case 1: {
					/* Get customer path file */
					System.out.printf(Constant.INPUT_CUSTOMER);
					System.out.println();
					customerCsvPath = sc.nextLine().trim();
					System.out.println();

					/* Get email template path file */
					System.out.printf(Constant.INPUT_EMAIL_TEMPLATE);
					System.out.println();
					emailTemplatePath = sc.nextLine().trim();
					System.out.println();

					/* Get outpu email folder path */
					System.out.printf(Constant.INPUT_OUTPUT_EMAIL);
					System.out.println();
					outputEmailFolderPath = sc.nextLine().trim();
					try {
						mainService.outputEmail(customerCsvPath, emailTemplatePath, outputEmailFolderPath);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				}

				/* Case close application */
				case 0: {
					/* Close connection had opened */
					sc.close();
					System.out.println(Constant.SYSTEM_EXIT);
					System.exit(0);
				}

				/* Case default when you choice wrong */
				default: {
					System.out.println(Constant.INPUT_CHOICE_INVALID);
				}
			}

		}
	}

}
