import { makeStyles } from '@material-ui/core';
import React from 'react';
import { FieldProps, FileField, FileFieldProps, useRecordContext } from 'react-admin';

const useStyles = makeStyles({
	file: {
		background: '#f1f3f5',
		padding: '20px',
		borderRadius: '10px',
		display: 'flex',
		'& a': {
			fontWeight: 'bold',
			color: '#495057',
			textDecoration: 'none',
		},
	},
});

const FileCustomField = (props: FileFieldProps) => {
	const classes = useStyles();
	return <FileField {...props} className={classes.file} fullWidth />;
};

export default FileCustomField;
