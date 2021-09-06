import * as React from 'react';
import { Card, CardContent, CardHeader } from '@material-ui/core';
import { Title } from 'react-admin';
import Test from '../typescript/Test';

export default () => (
	<Card>
		<Title title="Custom page" />
		<CardHeader title="CustomPage" />
		<CardContent>
			<Test />
		</CardContent>
	</Card>
);
