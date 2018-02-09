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



