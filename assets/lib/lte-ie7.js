/* Load this script using conditional IE comments if you need to support IE 7 and IE 6. */

window.onload = function() {
	function addIcon(el, entity) {
		var html = el.innerHTML;
		el.innerHTML = '<span style="font-family: \'icomoon\'">' + entity + '</span>' + html;
	}
	var icons = {
			'icon-user' : '&#xe000;',
			'icon-users' : '&#xe001;',
			'icon-users-2' : '&#xe002;',
			'icon-user-2' : '&#xe003;',
			'icon-bubble' : '&#xe004;',
			'icon-bubbles' : '&#xe005;',
			'icon-bubbles-2' : '&#xe006;',
			'icon-bubbles-3' : '&#xe007;',
			'icon-bubbles-4' : '&#xe008;',
			'icon-bubble-2' : '&#xe009;',
			'icon-pushpin' : '&#xe00a;',
			'icon-phone' : '&#xe00b;',
			'icon-settings' : '&#xe00c;',
			'icon-cog' : '&#xe00d;',
			'icon-cog-2' : '&#xe00e;',
			'icon-list' : '&#xe00f;',
			'icon-facebook' : '&#xe010;',
			'icon-google-plus' : '&#xe011;',
			'icon-twitter' : '&#xe012;',
			'icon-feed' : '&#xe013;',
			'icon-flickr' : '&#xe014;',
			'icon-picassa' : '&#xe015;',
			'icon-droplet' : '&#xe016;',
			'icon-droplet-2' : '&#xe017;',
			'icon-list-2' : '&#xe018;',
			'icon-refresh' : '&#xe019;',
			'icon-plus' : '&#xe01a;',
			'icon-trashcan' : '&#xe01b;',
			'icon-podcast' : '&#xe01c;',
			'icon-warning' : '&#xe01d;',
			'icon-marker' : '&#xe01e;',
			'icon-target' : '&#xe01f;',
			'icon-pencil' : '&#xe020;',
			'icon-radio-checked' : '&#xe021;',
			'icon-radio-unchecked' : '&#xe022;',
			'icon-checkbox-partial' : '&#xe023;',
			'icon-checkbox-unchecked' : '&#xe024;',
			'icon-github' : '&#xe025;'
		},
		els = document.getElementsByTagName('*'),
		i, attr, html, c, el;
	for (i = 0; ; i += 1) {
		el = els[i];
		if(!el) {
			break;
		}
		attr = el.getAttribute('data-icon');
		if (attr) {
			addIcon(el, attr);
		}
		c = el.className;
		c = c.match(/icon-[^\s'"]+/);
		if (c && icons[c[0]]) {
			addIcon(el, icons[c[0]]);
		}
	}
};