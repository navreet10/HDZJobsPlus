package services;

import dao.InterviewDao;
import model.HdzApplication;

public class InterviewService {

	public static Object getCodingTest(long id) {
		return InterviewDao.getCosingTest(id);
	}

	public static HdzApplication getHdzApplication(String appid) {
		return InterviewDao.getHdzApplication(appid);
	}

	public static void updateApplication(HdzApplication hdzApplication) {
		InterviewDao.updateApplication(hdzApplication);
		
	}

}
