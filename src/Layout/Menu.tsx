import BookIcon from '@material-ui/icons/Book';
import PeopleIcon from '@material-ui/icons/People';
import LabelIcon from '@material-ui/icons/Label';
import ListIcon from '@material-ui/icons/List';
import { DashboardMenuItem, MenuItemLink, MenuProps } from 'react-admin';
import { makeStyles } from '@material-ui/core';
import SubMenu from './SubMenu';
import { useState } from 'react';

const useStyles = makeStyles(theme => ({
	root: {
		marginTop: theme.spacing(1),
	},
}));

type MenuName = 'menuPost';

const Menu = ({ dense = false }: MenuProps) => {
	const classes = useStyles();
	const [openMenu, setOpenMenu] = useState({
		menuPost: false,
	});

	const handleToggle = (menu: MenuName) => {
		setOpenMenu(openMenu => ({ ...openMenu, [menu]: !openMenu[menu] }));
	};

	return (
		<div className={classes.root}>
			<DashboardMenuItem />
			<MenuItemLink to="/users" primaryText="사용자 관리" leftIcon={<PeopleIcon />} />
			<SubMenu
				handleToggle={() => handleToggle('menuPost')}
				isOpen={openMenu.menuPost}
				name="게시글"
				icon={<BookIcon />}
				dense={dense}
			>
				<MenuItemLink to="/posts" primaryText="Posts" leftIcon={<BookIcon />} />
				<MenuItemLink to="/tags" primaryText="Tags" leftIcon={<LabelIcon />} />
			</SubMenu>
			<MenuItemLink to="/custom" primaryText="CustomPage" leftIcon={<ListIcon />} />
		</div>
	);
};

export default Menu;
