import { Card, CardContent, CardHeader, makeStyles } from '@material-ui/core';
import React from 'react';
import {
	EditButton,
	Identifier,
	Record,
	RecordMap,
	ShowButton,
	TextField,
	UrlField,
} from 'react-admin';

const useStyles = makeStyles(theme => ({
	root: { margin: '1em' },
	card: {
		height: '100%',
		display: 'flex',
		flexDirection: 'column',
		margin: '0.5rem 0',
	},
	cardTitleContent: {
		display: 'flex',
		flexDirection: 'row',
		alignItems: 'center',
		justifyContent: 'space-between',
	},
	cardContent: {
		...theme.typography.body1,
		display: 'flex',
		flexDirection: 'column',
	},
}));

interface MobileGridProps {
	ids: Identifier[];
	data: RecordMap<Record>;
	basePath?: string;
}

const MobileGrid = ({ ids, data, basePath }: MobileGridProps) => {
	const classes = useStyles();
	return (
		<div style={{ margin: '1em' }}>
			{ids.map(id => (
				<Card key={id} className={classes.card}>
					<CardHeader
						title={
							<div className={classes.cardTitleContent}>
								<h2>{data[id].name}</h2>
								<ShowButton resource="visitors" basePath={basePath} record={data[id]} />
							</div>
						}
					/>
					<CardContent className={classes.cardContent}>
						<div>
							아이디: &nbsp;
							<TextField record={data[id]} source="id" />
						</div>
						<div>
							사용자이름: &nbsp;
							<TextField record={data[id]} source="username" />
						</div>
						<div>
							Email: &nbsp;
							<UrlField record={data[id]} source="email" />
						</div>
						<div>
							주소: &nbsp;
							<TextField record={data[id]} source="address.street" />
						</div>
						<div>
							전화번호: &nbsp;
							<TextField record={data[id]} source="phone" />
						</div>
					</CardContent>
				</Card>
			))}
		</div>
	);
};

MobileGrid.defaultProps = {
	data: {},
	ids: [],
};

export default MobileGrid;
