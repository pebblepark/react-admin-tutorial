import {
	Box,
	Card,
	Collapse,
	List,
	ListItem,
	ListItemSecondaryAction,
	ListItemText,
} from '@material-ui/core';
import ExpandLess from '@material-ui/icons/ExpandLess';
import ExpandMore from '@material-ui/icons/ExpandMore';
import * as React from 'react';
import { Fragment, useState } from 'react';
import {
	BooleanField,
	DeleteButton,
	EditButton,
	ListActions,
	ListBase,
	ListProps,
	Title,
	useListContext,
} from 'react-admin';
import { Tag } from './types';

const TagList = (props: ListProps) => (
	<ListBase perPage={1000} {...props}>
		<ListActions />
		<Box maxWidth="35em" marginTop="1em">
			<Card>
				<Tree />
			</Card>
		</Box>
	</ListBase>
);

const Tree = () => {
	const { ids, data, defaultTitle } = useListContext<Tag>();
	const [openChildren, setOpenChildren] = useState<Array<string>>([]);
	const toggleNode = (node: Tag) =>
		setOpenChildren((state: Array<string>) => {
			if (state.includes(node.id)) {
				return [
					...state.splice(0, state.indexOf(node.id)),
					...state.splice(state.indexOf(node.id) + 1, state.length),
				];
			} else {
				return [...state, node.id];
			}
		});
	const nodes = ids.map(id => data[id]);
	const roots = nodes.filter(node => node.parentId === null);
	const getChildNodes = (root: Tag) => nodes.filter(node => node.parentId === root.id);
	return (
		<List>
			<Title defaultTitle={defaultTitle} />
			{roots.map(root => (
				<SubTree
					key={root.id}
					root={root}
					getChildNodes={getChildNodes}
					openChildren={openChildren}
					toggleNode={toggleNode}
					level={1}
				/>
			))}
		</List>
	);
};

interface SubTreeProps {
	level: number;
	root: Tag;
	getChildNodes: (root: Tag) => Array<Tag>;
	openChildren: Array<string>;
	toggleNode: Function;
}

const SubTree = ({ level, root, getChildNodes, openChildren, toggleNode }: SubTreeProps) => {
	const childNodes = getChildNodes(root);
	const hasChildren: any = childNodes.length > 0;
	const open = openChildren.includes(root.id);
	return (
		<Fragment>
			<ListItem
				button={hasChildren}
				onClick={() => hasChildren && toggleNode(root)}
				style={{ paddingLeft: level * 16 }}
			>
				{hasChildren && open && <ExpandLess />}
				{hasChildren && !open && <ExpandMore />}
				{!hasChildren && <div style={{ width: 24 }}>&nbsp;</div>}
				<ListItemText primary={root.name} />

				<ListItemSecondaryAction>
					<EditButton record={root} basePath="/tags" />
					<DeleteButton record={root} basePath="/tags" />
				</ListItemSecondaryAction>
			</ListItem>
			<Collapse in={open} timeout="auto" unmountOnExit>
				<List component="div" disablePadding>
					{childNodes.map(node => (
						<SubTree
							key={node.id}
							root={node}
							getChildNodes={getChildNodes}
							openChildren={openChildren}
							toggleNode={toggleNode}
							level={level + 1}
						/>
					))}
				</List>
			</Collapse>
		</Fragment>
	);
};

export default TagList;
