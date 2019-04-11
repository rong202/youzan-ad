SELECT
	table_schema,
	table_name,
	column_name,
	ordinal_position
FROM
	information_schema.`COLUMNS`
WHERE
	TABLE_SCHEMA = 'youzan-ad'
AND TABLE_NAME = 'ad_unit_keyword'