create table apat(PATENT int,GYEAR int,GDATE int, APPYEAR int, COUNTRY string, POSTATE int,
                  ASSIGNEE int, ASSCODE int,CLAIMS int, NCLASS int, CAT int, SUBCAT int, CMADE int, CRECEIVE int, RATIOCIT int,
                  GENERAL int, ORIGINAL int, FWDAPLAG int, BCKGTLAG int, SELFCTUB int, SELFCTLB int, SECDUPBD int, SECDLWBD int)
  row format delimited
  FIELDS TERMINATED by ','
  LINES TERMINATED by '\n'
  stored as textfile;



java.lang.IllegalArgumentException: Unable to create serializer "org.apache.hive.com.esotericsoftware.kryo.serializers.FieldSerializer"
for class: org.apache.hadoop.hive.ql.io.HiveNullValueSequenceFileOutputFormat




drop table hbase_splits;
CREATE EXTERNAL TABLE IF NOT EXISTS hbase_splits(pdate STRING)
ROW FORMAT
  SERDE 'org.apache.hadoop.hive.serde2.binarysortable.BinarySortableSerDe'
STORED AS
  INPUTFORMAT 'org.apache.hadoop.mapred.TextInputFormat'
  OUTPUTFORMAT 'org.apache.hadoop.hive.ql.io.HiveNullValueSequenceFileOutputFormat'
LOCATION '/home/sundy/hd/hive/current_data/hbase/user_order/hbase_splits_out';

-- create a location to store the resulting HFiles
drop table hbase_hfiles;
CREATE TABLE if not exists hbase_hfiles(rowkey STRING, pageviews STRING)
STORED AS
  INPUTFORMAT 'org.apache.hadoop.mapred.TextInputFormat'
  OUTPUTFORMAT 'org.apache.hadoop.hive.hbase.HiveHFileOutputFormat'
TBLPROPERTIES('hfile.family.path' = '/home/sundy/hd/hive/current_data/hbase/user_order/w');

ADD JAR /home/sundy/hd/hive/lib/hive-contrib-2.1.1.jar;
SET mapred.reduce.tasks=1;

CREATE TEMPORARY FUNCTION row_seq AS 'org.apache.hadoop.hive.contrib.udf.UDFRowSequence';

-- input file contains ~4mm records. Sample it so as to produce 5 input splits.
INSERT OVERWRITE TABLE hbase_splits
SELECT PATENT FROM
  (SELECT PATENT, row_seq() AS seq FROM apat tablesample(bucket 1 out of 1000 on PATENT) s
order by PATENT
limit 1000) x
WHERE (seq % 30) = 0
ORDER BY PATENT
LIMIT 4;



