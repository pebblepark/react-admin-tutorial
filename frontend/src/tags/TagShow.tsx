import * as React from 'react';
import {
	BooleanField,
	Show,
	ShowProps,
	SimpleShowLayout,
	TextField,
	TranslatableFields,
} from 'react-admin';

const TagShow = (props: ShowProps) => (
	<Show {...props}>
		<SimpleShowLayout>
			<TextField source="id" />
			<TranslatableFields locales={['en', 'fr']}>
				<TextField source="name" />
			</TranslatableFields>
			<BooleanField source="published" />
		</SimpleShowLayout>
	</Show>
);

export default TagShow;
