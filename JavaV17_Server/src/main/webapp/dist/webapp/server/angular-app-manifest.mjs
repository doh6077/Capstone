
export default {
  bootstrap: () => import('./main.server.mjs').then(m => m.default),
  inlineCriticalCss: true,
  baseHref: '/',
  locale: undefined,
  routes: undefined,
  entryPointToBrowserMapping: {},
  assets: {
    'index.csr.html': {size: 52210, hash: '118a992b23000fa785ca4f1a0aab8d5bc1342f8c2059fbed5f7d3dd62a8c3dbd', text: () => import('./assets-chunks/index_csr_html.mjs').then(m => m.default)},
    'index.server.html': {size: 1113, hash: '1b82ee12bb8abb0555dc50545911c8987f1748207ec38b2ee88d0e1caff52761', text: () => import('./assets-chunks/index_server_html.mjs').then(m => m.default)},
    'styles-DULFY3JM.css': {size: 321271, hash: '3a3MbUcQiA4', text: () => import('./assets-chunks/styles-DULFY3JM_css.mjs').then(m => m.default)}
  },
};
