import React from 'react';

type TestDivProps = {
	a: string;
	b: any;
	c: number;
};

interface TestDivProps2 {
	a: string;
	b: any;
	c: number;
}

const Test = () => {
	let a = 'a';
	const aa: string = 'a';

	// a = 1;

	let b;
	b = 123;
	b = 'aaa';

	let c: number;
	c = 123;
	// c = 'aaa';

	return (
		<div>
			<TestDiv a={a} b={b} c={c} />
		</div>
	);
};

const TestDiv = (props: TestDivProps) => {
	const { a, b, c } = props;

	const print = (value: any) => {
		return `value: ${value}, type: ${typeof value}`;
	};

	return (
		<div>
			<p>a = {print(a)}</p>
			<p>b = {print(b)}</p>
			<p>c = {print(c)}</p>
		</div>
	);
};

export default Test;
