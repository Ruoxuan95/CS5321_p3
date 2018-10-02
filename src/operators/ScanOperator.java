package operators;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import data.DataBase;

import data.Tuple;

//Deal with Select * From Table
public class ScanOperator extends Operator{
	
	private String tableName;
	private String tableAddress;
	private File tableFile;
	private RandomAccessFile readPointer;
	private String tableAliase;
	private LinkedList<String> attributes;
	

	@Override
	public Tuple getNextTuple() {
		try {
			String data = readPointer.readLine();
			if (data!=null) {
				//Handle aliases
				Tuple tuple = new Tuple(data, tableAliase, attributes);
				return tuple;
			}
		} catch (IOException e) {
			e.printStackTrace();
			e.getMessage();
		}
		
		return null;
	}

	@Override
	public void reset() {
		try {
			this.readPointer.seek(0);
		} catch (IOException e) {
			e.printStackTrace();
			e.getMessage();
		}
		
	}
	
	//Constructors
	public ScanOperator() {
		
	}
	public ScanOperator(String tableInfo) {
		String[] aimTable = tableInfo.split("\\s+");
		if (aimTable.length<1) {
			this.tableName = null;
			return;
		}
		this.tableName = aimTable[0];
		this.tableAddress = DataBase.getInstance().getAddresses(tableName);
		this.tableFile = new File(tableAddress);
		try {
			this.readPointer = new RandomAccessFile(this.tableFile, "r");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			e.getMessage();
		}
		this.tableAliase = aimTable[aimTable.length-1];
		this.attributes = DataBase.getInstance().getSchema(tableName);
	}
	
	public ScanOperator(String tableName, String tableAliase) {
		this.tableName = tableName;
		this.tableAddress = DataBase.getInstance().getAddresses(tableName);
		this.tableFile = new File(tableAddress);
		try {
			this.readPointer = new RandomAccessFile(this.tableFile, "r");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			e.getMessage();
		}
		if (tableAliase == null) this.tableAliase = tableName;
		else this.tableAliase = tableAliase;
		this.attributes = DataBase.getInstance().getSchema(tableName);
	}
	
	public ScanOperator(String tableName, String tableAliase, Operator op) {
		setParent(op);
		this.tableName = tableName;
		this.tableAddress = DataBase.getInstance().getAddresses(tableName);
		this.tableFile = new File(tableAddress);
		try {
			this.readPointer = new RandomAccessFile(this.tableFile, "r");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			e.getMessage();
		}
		if (tableAliase == null) this.tableAliase = tableName;
		else this.tableAliase = tableAliase;
		this.attributes = DataBase.getInstance().getSchema(tableName);
	}
	
	//Getters
	public String getTableAliase() {
		return tableAliase;
	}
	public LinkedList<String> getAttributes(){
		return attributes;
	}

	public String getTableName() {
		return tableName;
	}

	public String getTableAddress() {
		return tableAddress;
	}

	public File getTableFile() {
		return tableFile;
	}

	public RandomAccessFile getReadPointer() {
		return readPointer;
	}
	

	
	

}
