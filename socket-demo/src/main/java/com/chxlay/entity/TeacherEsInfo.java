package com.chxlay.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 教师信息（字段来源类教师认证：EduTeacherAuth）
 *
 * @author Alay
 * @date 2020-11-18 15:22
 * @project Braineex
 */
@Data
public class TeacherEsInfo {
	private static final long serialVersionUID = 1L;


	private String userId;
	private String sex;
	private String major;
	private List<EduInfo> grades;
	private List<EduInfo> subjects;
	private List<EduInfo> bookVersions;
	private Qualify qualifyInfo;
	private Boolean isPass;
	private String vitae;
	private Integer taskCount;
	private Double star;
	private Integer ranking;

	private Boolean isFree;
	private Boolean isOnline;

	private LocalDateTime createTime;
	/**
	 * ES 数据不存储的字段
	 */
	private Boolean isCalled;
	/**
	 * ES 数据不存储的字段
	 */
	private String state;
	/**
	 * ES 数据不存储的字段
	 */
	private Long updateStamp;

	public TeacherEsInfo userId(String userId) {
		this.userId = userId;
		return this;
	}

	public TeacherEsInfo isFree(Boolean isFree) {
		this.isFree = isFree;
		return this;
	}

	public TeacherEsInfo isOnline(Boolean isOnline) {
		this.isOnline = isOnline;
		return this;
	}

	/**
	 * 教材,教学年纪,教学科目数据对象
	 */
	@Data
	static class EduInfo {
		private String dataId;
		private String grade;
		private String content;
	}

	/**
	 * 教师资格认证数据
	 */
	@Data
	private static class Qualify {
		/**
		 * 教师姓名
		 */
		private String name;
		/**
		 * 教师资格证等级
		 */
		private String teacherRank;
		/**
		 * 证书颁发日期
		 */
		private LocalDate awardDate;
	}
}