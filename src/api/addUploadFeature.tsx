import dataProvider from './dataProvider';

const myDataProvider = {
	...dataProvider,
	create: async (resource: string, params: any) => {
		const file = params.data.file;
		const files = params.data.files;

		if (!file && files) {
			return dataProvider.crate(resource, params);
		}

		const param = { ...params, data: { ...params.data } };
		if (file) {
			const isFile = file.rawFile instanceof File;
			isFile &&
				(await convertFileToBase64(file).then(base64File => (param.data.file = base64File)));
		}

		if (files) {
			if (files instanceof Array === false) return;
			const newFiles = files.filter((p: any) => p.rawFile instanceof File);
			const formerFiles = files.filter((p: any) => !(p.rawFile instanceof File));

			await newFiles
				.map(convertFileToBase64)
				.then((base64Files: any) => (param.data.files = [...base64Files, ...formerFiles]));
		}

		console.log(param);
		return dataProvider.create(resource, param);
	},
};

export const convertFileToBase64 = ({ rawFile }: { rawFile: File }) =>
	new Promise((resolve, reject) => {
		const reader = new FileReader();
		reader.onload = () =>
			resolve({ base64: reader.result, fileSize: rawFile.size, fileName: rawFile.name });
		reader.onerror = reject;

		reader.readAsDataURL(rawFile);
	});

export default myDataProvider;
