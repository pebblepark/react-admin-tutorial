import React from 'react';
import { FormDataConsumer, BooleanInput } from 'react-admin';

interface FormattedBooleanInputProps {
	format: (value: any) => boolean;
	parse: (value: any) => any;
	source: string;
	label?: string;
	defaultValue?: boolean;
	defaultChecked?: boolean;
}
const FormattedBooleanInput = ({
	format,
	parse,
	source,
	label,
	defaultValue,
	defaultChecked,
}: FormattedBooleanInputProps) => (
	<FormDataConsumer>
		{({ formData }) => (
			<BooleanInput
				options={{
					checked: format(formData[source]),
				}}
				format={format}
				parse={parse}
				source={source}
				label={label}
				defaultValue={defaultValue}
				defaultChecked={defaultChecked}
			/>
		)}
	</FormDataConsumer>
);

export default FormattedBooleanInput;
