CREATE TABLE item_cbo (
	Item_Image_FileName varchar2(256 char), 
	Item_Style varchar2(50 char),
	Item_Name varchar2(100 char), 
	Item_Bar_Code varchar2(50 char),
	Ref_Field8 varchar2(25 char),
	REF_DATE_FIELD3_DTTM varchar2(25 char),
	Ref_Field11 varchar2(25 char),
	REF_DATE_FIELD2_DTTM varchar2(25 char),
	Ref_Field7 varchar2(50 char),
	Ref_Field38 varchar2(50 char)
	);

CREATE TABLE item_supplier_xref_cbo(
	Item_Barcode varchar2(50 char),
	Supplier_Item_Barcode VARCHAR2(20 CHAR)
);

INSERT INTO item_cbo (Item_Image_FileName, Item_Style, Item_Name, Item_Bar_Code, Ref_Field8, REF_DATE_FIELD3_DTTM, Ref_Field11, REF_DATE_FIELD2_DTTM, Ref_Field7, Ref_Field38)
VALUES ('http://staging.scene7.com/is/image/dkscdn/DFJACJGNAOAKJHAM?&wid=300&hei=300', '462005', '40213892', '38495178345', '1', 'Y', '0', 'N',  '0', 'N');

INSERT INTO item_supplier_xref_cbo(Item_Barcode, Supplier_Item_Barcode)
VALUES ('38495178345', '38495178345')
	
	
	
	
	