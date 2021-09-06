import { Card, CardContent, Grid, makeStyles, Typography } from '@material-ui/core';
import React from 'react';
import {
	DateInput,
	email,
	FormWithRedirect,
	regex,
	required,
	TextInput,
	Toolbar,
} from 'react-admin';

const useStyles = makeStyles({
	root: { width: '50%' },
	item: { paddingRight: '15px' },
});

const UserForm = (props: any) => {
	const classes = useStyles();

	return (
		<FormWithRedirect
			{...props}
			render={(formProps: any) => (
				<Card>
					<CardContent>
						<Grid container className={classes.root}>
							<Grid item xs={12} className={classes.item}>
								<SectionTitle label="Required" />
							</Grid>
							<Grid item xs={6} className={classes.item}>
								<TextInput
									autoFocus
									source="name"
									label="이름"
									validate={requiredValidate}
									fullWidth
								/>
							</Grid>
							<Grid item xs={6} className={classes.item}>
								<TextInput
									source="username"
									label="사용자 이름"
									validate={requiredValidate}
									fullWidth
								/>
							</Grid>
							<Grid item xs={12} className={classes.item}>
								<TextInput
									type="email"
									source="email"
									validation={{ email: true }}
									fullWidth
									validate={[required(), email()]}
								/>
							</Grid>
							<Grid item xs={12} className={classes.item}>
								<SectionTitle label="Infomation" />
							</Grid>
							<Grid item xs={8} className={classes.item}>
								<TextInput source="phone" label="전화번호" fullWidth validate={phoneValidate} />
							</Grid>
							<Grid item xs={4} className={classes.item}>
								<DateInput source="birth" label="생년월일" fullWidth />
							</Grid>
							<Grid item xs={12} className={classes.item}>
								<TextInput source="website" label="사이트" fullWidth validate={websiteValidate} />
							</Grid>
							<Grid item xs={12} className={classes.item}>
								<SectionTitle label="Address" />
							</Grid>
							<Grid item xs={8} className={classes.item}>
								<TextInput source="address.street" label="주소" fullWidth />
							</Grid>
							<Grid item xs={4} className={classes.item}>
								<TextInput source="address.zipcode" label="우편번호" fullWidth helperText={false} />
							</Grid>
							<Grid item xs={12} className={classes.item}>
								<SectionTitle label="Company" />
							</Grid>
							<Grid item xs={12} className={classes.item}>
								<TextInput source="company.name" label="회사명" fullWidth />
							</Grid>
						</Grid>
					</CardContent>
					<Toolbar
						record={formProps.record}
						basePath={formProps.basePath}
						undoable={true}
						invalid={formProps.invalid}
						handleSubmit={formProps.handleSubmit}
						saving={formProps.saving}
						resource="post"
					/>
				</Card>
			)}
		/>
	);
};

const phoneValidate = regex(
	/^[0-9]{3}[-]+[0-9]{4}[-]+[0-9]{4}$/,
	'올바른 전화번호 형식이 아닙니다.',
);

const websiteValidate = regex(
	/[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b([-a-zA-Z0-9()@:%_\+.~#?&//=]*)/,
	'올바른 URL을 입력해주세요',
);

const requiredValidate = [required()];

const SectionTitle = ({ label }: { label: string }) => {
	return (
		<Typography variant="h6" gutterBottom>
			{label}
		</Typography>
	);
};

export default UserForm;
