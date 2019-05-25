package com.mihailya.coursework.accessDevice.data.util;

import java.util.ResourceBundle;

public class SqlStatementsManager {
	private static SqlStatementsManager instance;

	public static SqlStatementsManager getInstance() {
		if(instance == null)
			instance = new SqlStatementsManager();
		return instance;
	}

	private static final String BUNDLE_NAME = "sqlStatements";

	public enum OperationType{
		SELECT_ALL("SelectAll"),
		SELECT_BY_ID("SelectById"),
		INSERT("Insert"),
		UPDATE("Update"),
		DELETE("Delete"),
		DELETE_ALL("DeleteAll"),
		FIND("Find");

		private String operationPrefix;

		OperationType(String operationPrefix) {
			this.operationPrefix = operationPrefix;
		}

		public String getOperationPrefix() {
			return operationPrefix;
		}
	}

	private ResourceBundle resourceBundle;

	private SqlStatementsManager() {
		resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
	}

	public String getSqlStatement(Class entityClass, OperationType operationType) {
		String statementId = entityClass.getSimpleName() + operationType.getOperationPrefix();

		return resourceBundle.getString(statementId);
	}

}
