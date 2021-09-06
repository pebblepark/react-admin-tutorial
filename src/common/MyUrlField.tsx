import { makeStyles } from '@material-ui/core';
import LaunchIcon from '@material-ui/icons/Launch';
import React from 'react';
import { useRecordContext } from 'react-admin';

const useStyles = makeStyles({
	link: {
		textDecoration: 'none',
	},
	icon: {
		width: '0.5em',
		height: '0.5em',
		paddingLeft: 2,
	},
});

export interface MyUrlFieldProps {
	source: string;
	addLabel?: boolean;
	label?: string;
	emptyText?: string;
}

const MyUrlField = ({ source }: MyUrlFieldProps) => {
	const record = useRecordContext();
	const classes = useStyles();
	return record ? (
		<a href={record[source]} className={classes.link}>
			{record[source]}
			<LaunchIcon className={classes.icon} />
		</a>
	) : null;
};

export default MyUrlField;
