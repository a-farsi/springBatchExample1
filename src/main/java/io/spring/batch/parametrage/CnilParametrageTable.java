package io.spring.batch.parametrage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "table_parametrage_cnil")
public class CnilParametrageTable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long traitementTypeId;

	@Column(name = "type_traitement")
	private String processingType;

	@Column(name = "libelle")
	private String label;

	@Column(name = "nom_table")
	private String tableName;

	@Column(name = "nom_colonne")
	private String columnName;

	public CnilParametrageTable() {
		super();
	}

	public CnilParametrageTable(Long traitementTypeId, String processingType, String label, String tableName,
			String columnName) {
		super();
		this.traitementTypeId = traitementTypeId;
		this.processingType = processingType;
		this.label = label;
		this.tableName = tableName;
		this.columnName = columnName;
	}

	public String getProcessingType() {
		return processingType;
	}

	public void setProcessingType(String processingType) {
		this.processingType = processingType;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	@Override
	public String toString() {
		return "CnilParametrageTable [traitementTypeId=" + traitementTypeId + ", processingType=" + processingType
				+ ", label=" + label + ", tableName=" + tableName + ", columnName=" + columnName + "]";
	}

}
