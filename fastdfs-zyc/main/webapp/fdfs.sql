/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.40.51
 Source Server Type    : MySQL
 Source Server Version : 50161
 Source Host           : 192.168.40.51
 Source Database       : fdfs

 Target Server Type    : MySQL
 Target Server Version : 50161
 File Encoding         : utf-8

 Date: 09/13/2012 16:33:39 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tbdownloadfilerecord`
-- ----------------------------
DROP TABLE IF EXISTS `tbdownloadfilerecord`;
CREATE TABLE `tbdownloadfilerecord` (
  `id` varchar(255) NOT NULL,
  `accessCount` bigint(20) NOT NULL,
  `fileId` varchar(255) DEFAULT NULL,
  `src_ip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `tbdownloadfilerecord`
-- ----------------------------
BEGIN;
INSERT INTO `tbdownloadfilerecord` VALUES ('4028a8e739bd41140139bd420d2a0000', '2', '/group1/M00/00/00/wKgoM1BE2-WhZVKOAAEMrV4kDUM856.jpg', '192.168.40.50'), ('4028a8e739bd41140139bd4223230001', '4', '/group1/M00/00/00/wKgoM1BE1RTT_L6JAAFHsYiUkzI460.jpg', '192.168.40.50'), ('4028a8e739bd41140139bd4229ca0002', '2', '/group1/M00/00/00/wKgoMlBE1ReS6bpSAAE0lkXGXJQ206.gif', '192.168.40.50'), ('4028a8e739bd41140139bd422c2c0003', '4', '/group1/M00/00/00/wKgoM1BE2-WhZVKOAAEMrV4kDUM856.jpg', '192.168.40.51'), ('4028a8e739bd41140139bd422ff50004', '2', '/group1/M00/00/00/wKgoM1BE1RTT_L6JAAFHsYiUkzI460.jpg', '192.168.40.51'), ('4028a8e739bd41140139bd4230df0005', '2', '/group1/M00/00/00/wKgoM1BE1S2yZGp1AAEMrV4kDUM901.jpg', '192.168.40.51'), ('4028a8e739bd41140139bd4231c90006', '2', '/group1/M00/00/00/wKgoMlBE20HykjhaAAEMrV4kDUM163.jpg', '192.168.40.51'), ('4028a8e739bd41140139bd4232d30007', '2', '/group1/M00/00/00/wKgoMlBE3AyzjOQzAAAt9DxlN_c2498.js', '192.168.40.51'), ('4028a8e739bd41140139bd42342b0008', '2', '/group1/M00/00/00/wKgoM1BFT1zQQXs-AABVROEPP7A60.html', '192.168.40.51');
COMMIT;

-- ----------------------------
--  Table structure for `tbfile`
-- ----------------------------
DROP TABLE IF EXISTS `tbfile`;
CREATE TABLE `tbfile` (
  `id` varchar(255) NOT NULL,
  `MD5` varchar(255) DEFAULT NULL,
  `article_id` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `file_id` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `fileSize` bigint(20) NOT NULL,
  `fileDownLoadCount` int(11) NOT NULL,
  `srcIpAddr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `tbfile`
-- ----------------------------
BEGIN;
INSERT INTO `tbfile` VALUES ('4028a8e7398ce09501398ce0d6dd0005', '4c0a53d20a53b7046582930be20d97cb', null, '2012-09-04 00:05:08', 'group1/M00/00/00/wKgoM1BE1RTT_L6JAAFHsYiUkzI460.jpg', '5.jpg', 'jpg', '83889', '0', null), ('4028a8e7398ce09501398ce129620006', 'a013e0264420806db90c820ff6b5eeb8', null, '2012-09-04 00:05:30', 'group1/M00/00/00/wKgoMlBE1ReS6bpSAAE0lkXGXJQ206.gif', '20.gif', 'gif', '78998', '0', null), ('4028a8e7398ce09501398ce13be00007', '1ad2d38acaaa14ea28cc70f9bd04b5a7', null, '2012-09-04 00:05:34', 'group1/M00/00/00/wKgoM1BE1S2yZGp1AAEMrV4kDUM901.jpg', '16.jpg', 'jpg', '68781', '0', null), ('4028a8e7398cf92401398cf93c970000', '1ad2d38acaaa14ea28cc70f9bd04b5a7', null, '2012-09-04 00:31:47', 'group1/M00/00/00/wKgoMlBE20HykjhaAAEMrV4kDUM163.jpg', '16.jpg', 'jpg', '68781', '0', null), ('4028a8e7398cf92401398cfb77740010', '1ad2d38acaaa14ea28cc70f9bd04b5a7', null, '2012-09-04 00:34:14', 'group1/M00/00/00/wKgoM1BE2-WhZVKOAAEMrV4kDUM856.jpg', '16.jpg', 'jpg', '68781', '0', null), ('4028a8e7398cf92401398cfc53ca0016', '4804e397c748e20a55d14283d7861ab1', null, '2012-09-04 00:35:10', 'group1/M00/00/00/wKgoMlBE3AyzjOQzAAAt9DxlN_c2498.js', 'indexdata.js', 'js', '11764', '0', null), ('4028a8e7398cf92401398cfc83560017', '4804e397c748e20a55d14283d7861ab1', null, '2012-09-04 00:35:22', 'group1/M00/00/00/wKgoM1BE3Cmi7KtBAAAt9DxlN_c5029.js', 'indexdata.js', 'js', '11764', '0', null), ('4028a8e7398cf92401398cfd0caa0018', '6b49611b8b395edb89c04b8678b8c89b', null, '2012-09-04 00:35:57', 'group1/M00/00/00/wKgoMlBE3DqzzPsKAgHgUFnIGxg197.zip', 'bi.zip', 'zip', '33677392', '0', null), ('4028a8e7398cf92401398d00ca5e0032', 'be5fa315cc1a442ea690d0d2570bf41e', null, '2012-09-04 00:40:02', 'group1/M00/00/00/wKgoM1BE3UKQ0EHHAAP3eJCErFg033.pdf', 'FastDFS.pdf', 'pdf', '259960', '0', null), ('4028a8e7398cf92401398d00ee030033', 'be5fa315cc1a442ea690d0d2570bf41e', null, '2012-09-04 00:40:12', 'group1/M00/00/00/wKgoMlBE3TmRvGwKAAP3eJCErFg755.pdf', 'FastDFS.pdf', 'pdf', '259960', '0', null), ('4028a8e7398ebd9b01398ebe7f1f0005', '8bcbc9866e0e1560a18e5f42483a55f4', null, '2012-09-04 08:46:52', 'group1/M00/00/00/wKgoM1BFT1zQQXs-AABVROEPP7A60.html', '1.html', 'html', '21828', '0', null), ('4028a8e7398ebd9b01398ee65e7500e7', '8bcbc9866e0e1560a18e5f42483a55f4', null, '2012-09-04 09:30:25', 'group1/M00/00/00/wKgoMlBFWX_x70LiAABVROEPP7A38.html', '1.html', 'html', '21828', '0', null), ('4028a8e7398ebd9b01398ee764d900ed', '8bcbc9866e0e1560a18e5f42483a55f4', null, '2012-09-04 09:31:32', 'group1/M00/00/00/wKgoM1BFWdSTrWZeAABVROEPP7A92.html', '1.html', 'html', '21828', '0', null), ('4028a8e7398ebd9b01398eed091f010c', '8bcbc9866e0e1560a18e5f42483a55f4', null, '2012-09-04 09:37:42', 'group1/M00/00/00/wKgoMlBFWzSBY8JFAABVROEPP7A99.html', '1.html', 'html', '21828', '0', null), ('4028a8e7398ebd9b01398f16374c01f3', 'be5fa315cc1a442ea690d0d2570bf41e', null, '2012-09-04 10:22:41', 'group1/M00/00/00/wKgoM1BFZdCxLsL8AAP3eJCErFg769.pdf', 'FastDFS.pdf', 'pdf', '259960', '0', null), ('4028a8e7398f27cd01398f28b4450005', 'be5fa315cc1a442ea690d0d2570bf41e', null, '2012-09-04 10:42:53', 'group1/M00/00/00/wKgoMlBFanqQC78dAAP3eJCErFg233.pdf', 'FastDFS.pdf', 'pdf', '259960', '0', null), ('4028a8e7398f372901398f5068060091', '7d4ec7124f552c87bdb77ffa8a0a85ab', null, '2012-09-04 11:26:15', 'group1/M00/00/00/wKgoM1BFdLXwtaufAA9ygTTFvWw369.rar', 'ligerui.rar', 'rar', '1012353', '0', null), ('4028a809398ff57101398ff86c14000f', 'be5fa315cc1a442ea690d0d2570bf41e', null, '2012-09-04 14:29:46', 'group2/M00/00/00/wKgoMlBFn6Sx2jLSAAP3eJCErFg056.pdf', 'FastDFS.pdf', 'pdf', '259960', '0', null), ('4028a8e7398f37290139900bf18c04a2', '7d4ec7124f552c87bdb77ffa8a0a85ab', null, '2012-09-04 14:51:05', 'group1/M00/00/00/wKgoM1BFpLjDAFyIAA9ygTTFvWw232.rar', 'ligerui.rar', 'rar', '1012353', '0', null), ('4028a8e7398f37290139900c74c604a3', '8bcbc9866e0e1560a18e5f42483a55f4', null, '2012-09-04 14:51:39', 'group1/M00/00/00/wKgoMlBFpMfTum6TAABVROEPP7A61.html', '1.html', 'html', '21828', '0', null), ('4028a8e7398f37290139901046ec04b8', '99157587910b541a8d1b89d88eaf24ef', null, '2012-09-04 14:55:49', 'group2/M00/00/00/wKgoM1BFpdSCkcJ4AAAXOixPE4M507.jpg', 'jiantou.jpg', 'jpg', '5946', '0', null), ('4028a8e7398f372901399013c28604cd', 'd5c05e4921aa3c1589ab72ff986ed546', null, '2012-09-04 14:59:37', 'group2/M00/00/00/wKgoMlBFpqWy-slnAggsuBgZZ3A455.exe', 'Fetion.exe', 'exe', '34090168', '0', null), ('4028a8e7398f372901399013f1a404ce', 'd5c05e4921aa3c1589ab72ff986ed546', null, '2012-09-04 14:59:49', 'group2/M00/00/00/wKgoM1BFpsOA7h5oAggsuBgZZ3A479.exe', 'Fetion.exe', 'exe', '34090168', '0', null), ('ff80808139b3345d0139b411574d04d9', 'f8627c9832f53189b1598d3fdd65601f', null, '2012-09-11 14:43:18', 'group1/M00/00/01/wKgoM1BO3ZPDf0OQAKU1vn2Gty8178.pdf', '创新者的思考：发现创业与创意的源头+（日）大前研一着.pdf', 'pdf', '10827198', '0', null), ('ff80808139b3345d0139b408a49f04a6', 'f8627c9832f53189b1598d3fdd65601f', null, '2012-09-11 14:33:48', 'group1/M00/00/01/wKgoM1BO21nBFp2aAKU1vn2Gty8225.pdf', '创新者的思考：发现创业与创意的源头+（日）大前研一着.pdf', 'pdf', '10827198', '0', null), ('4028a8e739add8860139af0e5bec06be', '1541aca93aba2a1acd9ad09a2957208e', null, '2012-09-10 15:21:57', 'group1/M00/00/00/wKgoM1BNlSDgn0zBAAAZITxrLI093.aspx', 'index.aspx', 'aspx', '6433', '0', null), ('b27650d8-22ce-4656-949d-576990ea866e', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:22', 'group1/M00/00/00/wKgoMlBNW7yAVWyWAAzodQCbVVc621.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('249532fe-b9a3-4a2b-a045-263a2c9e0b47', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:20', 'group1/M00/00/00/wKgoM1BNW8DxY35rAAzodQCbVVc622.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('64931ece-7edf-481d-851b-2358c7e3b6c5', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:21', 'group1/M00/00/00/wKgoMlBNW7uiEse7AAzodQCbVVc209.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('01cf43cb-e881-46de-b011-99243d4321d6', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:21', 'group1/M00/00/00/wKgoM1BNW8DhphG8AAzodQCbVVc735.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('0b42e11c-7e25-45ad-9a58-bdb736baf3e1', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:21', 'group1/M00/00/00/wKgoMlBNW7vg0-lSAAzodQCbVVc916.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('c0f4712a-8006-4683-8a04-bf82408ae264', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:21', 'group1/M00/00/00/wKgoM1BNW8HQAuOGAAzodQCbVVc259.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('162e51c0-28ed-453c-bae6-357126cace5d', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:22', 'group1/M00/00/00/wKgoM1BNW8HRcCyNAAzodQCbVVc253.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('df097dd7-2272-4686-b669-af98ec8609b4', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:22', 'group1/M00/00/00/wKgoMlBNW7yTkgYoAAzodQCbVVc867.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('b9db8c0a-4e25-409a-9309-92bf15d84511', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:22', 'group1/M00/00/00/wKgoM1BNW8LiJQ08AAzodQCbVVc858.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('2ff17b9a-81dc-4cb2-a1e7-f3779c968aee', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:22', 'group1/M00/00/00/wKgoMlBNW73wdlaHAAzodQCbVVc454.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('41a30f94-fc21-49f7-95c9-1dccd2d2c198', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:23', 'group1/M00/00/00/wKgoM1BNW8KwCrw6AAzodQCbVVc061.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('f06a5424-32a5-47d2-bc2f-9532275711ec', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:23', 'group1/M00/00/00/wKgoMlBNW72g8xT0AAzodQCbVVc239.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('70725a9c-0394-4e31-8bfc-9f4ca4adb346', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:23', 'group1/M00/00/00/wKgoM1BNW8LCtFITAAzodQCbVVc278.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('3a1b234c-d777-4140-910d-040e5b85326e', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:23', 'group1/M00/00/00/wKgoMlBNW73A1FeKAAzodQCbVVc503.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('5ecf69f6-c5c2-40ff-b9b3-6198b74e5ab8', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:23', 'group1/M00/00/00/wKgoM1BNW8OjagX8AAzodQCbVVc581.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('7be56ba4-db6b-4e74-8ecb-dad6f12a22c8', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:24', 'group1/M00/00/00/wKgoMlBNW77wFB_zAAzodQCbVVc068.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('182df09c-c9a3-4d6c-8724-036564d327f4', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:24', 'group1/M00/00/00/wKgoM1BNW8Pgugi8AAzodQCbVVc258.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('cc4acaf3-7fa1-410e-9543-05088f961ba3', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:24', 'group1/M00/00/00/wKgoMlBNW76CXVB8AAzodQCbVVc315.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('ea39a58e-699a-4d2f-8f3a-2028fb38db32', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:24', 'group1/M00/00/00/wKgoM1BNW8Ow2xugAAzodQCbVVc163.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('e51ea7f3-7661-4ab9-ad0d-431781e9d9aa', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:24', 'group1/M00/00/00/wKgoMlBNW77SwpBAAAzodQCbVVc236.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('8011c214-e6be-4caf-9906-6947b9e3d567', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:24', 'group1/M00/00/00/wKgoM1BNW8SA_E2oAAzodQCbVVc800.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('239e6ed0-c621-44c7-9346-0fb50fdb22c5', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:25', 'group1/M00/00/00/wKgoMlBNW7-SNjnFAAzodQCbVVc550.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('41a48c51-e53b-4aa4-bf5c-bdc68ac2a954', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:25', 'group1/M00/00/00/wKgoM1BNW8SzPS8rAAzodQCbVVc358.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('db295fe6-727c-41a0-8aac-8c43e9ed0427', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:25', 'group1/M00/00/00/wKgoMlBNW7-R0fE5AAzodQCbVVc732.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('4f9b987b-07f7-4b07-835d-1d467b9cd04f', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:25', 'group1/M00/00/00/wKgoM1BNW8SwcCIFAAzodQCbVVc882.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('d0a9de50-fc5f-4225-a3bd-637ebd71bcd2', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:25', 'group1/M00/00/00/wKgoMlBNW7-RSFE_AAzodQCbVVc668.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('0a6b39b1-9ab4-4f7d-870d-50bbe45ee6f2', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:26', 'group1/M00/00/00/wKgoM1BNW8XTZNU2AAzodQCbVVc359.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('464b9fa4-ba50-4b8b-ba7f-ac7aaaf0e0b5', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:26', 'group1/M00/00/00/wKgoMlBNW8CgK2fxAAzodQCbVVc424.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('25d68398-4b83-448e-bd0e-c69c103abc6a', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:26', 'group1/M00/00/00/wKgoM1BNW8WwVw_IAAzodQCbVVc023.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('f1ba7a44-c7b1-4bbd-bd79-23bd7ad26ee4', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:26', 'group1/M00/00/00/wKgoMlBNW8DwsUq2AAzodQCbVVc146.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('55e1ceeb-29ca-4194-9543-589a4641535f', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:26', 'group1/M00/00/00/wKgoM1BNW8XiILdxAAzodQCbVVc804.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('69f49cda-f90d-4c96-aa0d-30372e2726c1', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:26', 'group1/M00/00/00/wKgoMlBNW8CRpBsYAAzodQCbVVc464.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('f8a1321c-e941-43f2-8755-ec8e2deb0f5d', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:27', 'group1/M00/00/00/wKgoM1BNW8bT2XbgAAzodQCbVVc833.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('b3bc834b-4036-4fe8-8bc6-220c259b0485', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:27', 'group1/M00/00/00/wKgoMlBNW8GA2jNdAAzodQCbVVc785.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('480ac090-aba5-4d18-b212-d41adb769620', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:27', 'group1/M00/00/00/wKgoM1BNW8bxHv3rAAzodQCbVVc146.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('a72cd075-56ac-49a6-8c3b-2d3fcdac85f2', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:27', 'group1/M00/00/00/wKgoMlBNW8HDY3X_AAzodQCbVVc166.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('e16658d7-fe7d-44a2-9eb6-130a6a150e41', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:27', 'group1/M00/00/00/wKgoM1BNW8fShxpDAAzodQCbVVc885.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('843b8adb-ae0e-45c1-8f67-126444f23310', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:27', 'group1/M00/00/00/wKgoMlBNW8HyivJ0AAzodQCbVVc257.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('e730581a-50d3-4bad-bf2f-8ab550bfd9d1', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:28', 'group1/M00/00/00/wKgoM1BNW8eQDEVzAAzodQCbVVc608.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('1ccccf0e-2717-4836-b58c-5fea9fca27c6', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:28', 'group1/M00/00/00/wKgoMlBNW8LApF5mAAzodQCbVVc074.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('ac850a1e-b01c-4817-94fd-a947097de47a', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:28', 'group1/M00/00/00/wKgoM1BNW8eTSu1WAAzodQCbVVc331.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('0ef7f692-28e7-44a0-8b27-8d4ccebe3d63', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:28', 'group1/M00/00/00/wKgoMlBNW8KwcmHyAAzodQCbVVc692.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('63753e50-9e73-4898-87f7-614273db667b', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:29', 'group1/M00/00/00/wKgoM1BNW8iS3q77AAzodQCbVVc505.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('75b35024-447c-4c65-acbb-38ecec2da900', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:29', 'group1/M00/00/00/wKgoMlBNW8OQzXjgAAzodQCbVVc312.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('f273e8be-84a4-4200-a340-830d09e9207b', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:29', 'group1/M00/00/00/wKgoM1BNW8iBPhylAAzodQCbVVc696.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('21c8d467-0268-461b-b343-6a78af009bcf', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:29', 'group1/M00/00/00/wKgoMlBNW8PzhPkLAAzodQCbVVc938.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('ed860300-8fcc-4f1c-891e-9012f521ec90', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:29', 'group1/M00/00/00/wKgoM1BNW8nz-8CLAAzodQCbVVc218.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('fb8f5b9d-ead9-48b7-a087-0f5fe8bad4ff', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:29', 'group1/M00/00/00/wKgoMlBNW8OSEDQQAAzodQCbVVc165.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('054ec5c0-f263-441f-bcd9-6d1497bb09ea', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:30', 'group1/M00/00/00/wKgoM1BNW8mwLPauAAzodQCbVVc469.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('28c1d17f-2b22-482c-9de8-7f062984034c', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:30', 'group1/M00/00/00/wKgoMlBNW8ShjqQjAAzodQCbVVc828.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('8c3c3e36-8b8e-484d-a26c-7cc19e36e9e7', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:30', 'group1/M00/00/00/wKgoM1BNW8nzvbDkAAzodQCbVVc497.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('0285c007-7251-412d-93b0-675b889d6688', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:30', 'group1/M00/00/00/wKgoMlBNW8TRcmc6AAzodQCbVVc972.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('d5de784a-9468-47e7-9ca3-af95e4f98ce6', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:31', 'group1/M00/00/00/wKgoM1BNW8rQufn0AAzodQCbVVc839.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('df2f2342-6d29-44a3-b450-8c8af445e769', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:31', 'group1/M00/00/00/wKgoMlBNW8WAUe9lAAzodQCbVVc628.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('067c3e64-574d-4b07-986f-f1bf77b2b49f', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:31', 'group1/M00/00/00/wKgoM1BNW8rwg9-xAAzodQCbVVc606.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('157312f6-9217-46c5-b1e2-af7adc822bd6', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:31', 'group1/M00/00/00/wKgoMlBNW8XQjIyuAAzodQCbVVc103.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('259252cd-9ddb-4d79-9f2e-52ad6d3f4610', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:32', 'group1/M00/00/00/wKgoM1BNW8uQyAD1AAzodQCbVVc261.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('da09be51-f43d-49bf-92c7-3d26881dd9a5', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:32', 'group1/M00/00/00/wKgoMlBNW8bA4LaVAAzodQCbVVc972.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('9d848383-1aac-442c-96e6-d6030c43a81d', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:32', 'group1/M00/00/00/wKgoM1BNW8uwN6byAAzodQCbVVc098.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('a352e4e7-a29a-4d46-852f-be0c849b0849', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:32', 'group1/M00/00/00/wKgoMlBNW8bhE_dSAAzodQCbVVc558.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('76249b59-61fe-40ff-86b3-7584469e6a3d', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:33', 'group1/M00/00/00/wKgoM1BNW8yQx7dpAAzodQCbVVc574.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('38f382a0-ea18-430f-b92b-e5e86bf10dea', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:33', 'group1/M00/00/00/wKgoMlBNW8ewpUmKAAzodQCbVVc775.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('c855ea1d-515a-4130-935d-04d178028b0b', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:33', 'group1/M00/00/00/wKgoM1BNW8zxThz9AAzodQCbVVc235.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('084f7b6f-7868-4f28-90c5-0dfffd1d6d35', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:33', 'group1/M00/00/01/wKgoMlBNW8fg4qz1AAzodQCbVVc666.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('b1f3f38e-4013-45fc-afac-b1ba6fe2e134', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:34', 'group1/M00/00/00/wKgoM1BNW82iyyGOAAzodQCbVVc839.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('dddfb93f-4853-480d-8b58-5e4ff613ec37', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:34', 'group1/M00/00/01/wKgoMlBNW8izBXVEAAzodQCbVVc568.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('2a3fba9b-fcbd-4053-a908-3d2bec3264ff', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:34', 'group1/M00/00/00/wKgoM1BNW82zronpAAzodQCbVVc389.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('d1dd65fc-9011-4bf6-a53e-07321d19ffbe', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:34', 'group1/M00/00/01/wKgoMlBNW8jhbDnEAAzodQCbVVc551.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('56403e4e-18c3-4c99-b641-2007a659e2b1', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:34', 'group1/M00/00/00/wKgoM1BNW87C0RwHAAzodQCbVVc613.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('52b9ade3-ad70-4ae5-a4bf-a78a0436ef1a', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:35', 'group1/M00/00/01/wKgoMlBNW8mRsZtXAAzodQCbVVc353.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('62fb265d-7ec0-48ec-b12e-2b943dcf69bb', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:35', 'group1/M00/00/00/wKgoM1BNW87jr7pgAAzodQCbVVc973.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('a650829a-2314-4ca4-a500-d1468e0c0ade', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:35', 'group1/M00/00/01/wKgoMlBNW8nxPAvRAAzodQCbVVc089.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('b57dc330-aba3-43a4-a8fe-f8155673d5ba', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:35', 'group1/M00/00/00/wKgoM1BNW87yLzwbAAzodQCbVVc521.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('a5c4b9d8-27f1-4c06-8706-62a05a19bcc5', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:35', 'group1/M00/00/01/wKgoMlBNW8qA9aiiAAzodQCbVVc055.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('ae0d4892-d476-4687-bda9-915691fe1522', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:36', 'group1/M00/00/00/wKgoM1BNW8_g0efaAAzodQCbVVc122.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('5c469ad3-1194-4f91-be71-f81855d64e41', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:36', 'group1/M00/00/01/wKgoMlBNW8qjaGJ7AAzodQCbVVc556.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('b3898cc9-cc99-4011-9d73-2f24f07f2cbd', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:36', 'group1/M00/00/00/wKgoM1BNW8_gM81wAAzodQCbVVc416.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('9d41129b-6a8f-45e6-b55b-f7c2a77c496c', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:36', 'group1/M00/00/01/wKgoMlBNW8vTYSFUAAzodQCbVVc897.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('eae8da28-9d62-42af-a110-071845f1e0be', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:37', 'group1/M00/00/00/wKgoM1BNW9CDcbIlAAzodQCbVVc839.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('311207f5-6d51-40d7-9bd3-fd154fa3e593', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:37', 'group1/M00/00/01/wKgoMlBNW8uiqGAiAAzodQCbVVc803.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('a74cf122-7553-4593-a87b-561e52853eb6', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:37', 'group1/M00/00/00/wKgoM1BNW9Dwa_rHAAzodQCbVVc778.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('411f45a3-65f7-46ca-9cfd-2369f4ede82b', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:37', 'group1/M00/00/01/wKgoMlBNW8vxePAXAAzodQCbVVc321.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('3ee18948-1ff4-4d42-9a7e-191d7e7dcdb0', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:37', 'group1/M00/00/00/wKgoM1BNW9HS6we1AAzodQCbVVc962.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('507d3722-d767-4101-82f8-b198d8ef58da', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:38', 'group1/M00/00/01/wKgoMlBNW8zB1L6fAAzodQCbVVc274.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('89175f9b-8524-4fcc-86a0-2940c69950d2', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:38', 'group1/M00/00/00/wKgoM1BNW9HCA0WfAAzodQCbVVc099.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('7ff15018-6ebd-420a-897a-2977eebf860a', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:38', 'group1/M00/00/01/wKgoMlBNW8zCXifHAAzodQCbVVc801.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('9f88740b-e24e-4727-9962-eee34d09dcde', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:38', 'group1/M00/00/00/wKgoM1BNW9GisAiZAAzodQCbVVc057.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('18b54dba-038f-4f5c-9fa1-5d54e15c6c01', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:38', 'group1/M00/00/01/wKgoMlBNW8ygTKeyAAzodQCbVVc825.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('7dbf2967-47fc-4ba8-86e5-cdc2cc3714db', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:38', 'group1/M00/00/00/wKgoM1BNW9KyDer5AAzodQCbVVc586.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('b6365890-03fe-46ad-a65c-1a7f9511b74d', 'ba45c8f60456a672e003a875e469d0eb', null, '2012-09-10 11:17:39', 'group1/M00/00/01/wKgoMlBNW83CKQcsAAzodQCbVVc313.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('f9908b79-6d05-46a8-af12-019834fa61f1', 'ba45c8f60456a672e003a875e469d0eb', '3', '2012-09-10 11:17:39', 'group1/M00/00/00/wKgoM1BNW9KTUegeAAzodQCbVVc571.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('07751602-3007-477c-95ad-0571d0ebddd3', 'ba45c8f60456a672e003a875e469d0eb', '3', '2012-09-10 11:17:39', 'group1/M00/00/01/wKgoMlBNW83A3coAAAzodQCbVVc067.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('ad8ce807-cb1e-4aa9-9341-d37350687b59', 'ba45c8f60456a672e003a875e469d0eb', '2', '2012-09-10 11:17:39', 'group1/M00/00/00/wKgoM1BNW9LRulHWAAzodQCbVVc388.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('15299484-5467-4629-a814-e3cc21a29c0b', 'ba45c8f60456a672e003a875e469d0eb', '2', '2012-09-10 11:17:39', 'group1/M00/00/01/wKgoMlBNW83gFrMoAAzodQCbVVc123.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('cad7c425-2e7d-4b30-b3a3-22f621fb77a4', 'ba45c8f60456a672e003a875e469d0eb', '2', '2012-09-10 11:17:40', 'group1/M00/00/00/wKgoM1BNW9PihLvBAAzodQCbVVc207.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('0bd08edd-986a-4b10-815f-65e1c84a2822', 'ba45c8f60456a672e003a875e469d0eb', '2', '2012-09-10 11:17:40', 'group1/M00/00/01/wKgoMlBNW86TSJbUAAzodQCbVVc198.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('edcbf66b-f040-4e3d-be94-9d5791a82ab3', 'ba45c8f60456a672e003a875e469d0eb', '3', '2012-09-10 11:17:40', 'group1/M00/00/00/wKgoM1BNW9OyLycaAAzodQCbVVc744.jpg', 'viva-fdfsDesert.jpg', 'jpg', '845941', '0', null), ('ff80808139b3345d0139b407a51d04a0', 'e057f8f6307810f12b7b782f8e53de92', null, '2012-09-11 14:32:43', 'group1/M00/00/01/wKgoM1BO2xiAcChvAHbZBOnCv0k337.pdf', '21世纪工商管理百科全书：管理艺术.pdf', 'pdf', '7788804', '0', null), ('ff80808139b3345d0139b40603770495', '6a9de8a6cfc94e44ae8ce8b3e963f94e', null, '2012-09-11 14:30:56', 'group1/M00/00/01/wKgoM1BO2q2RbaJFAITzsYuYeLg858.pdf', '21世纪工商管理百科全书：管理案例.pdf', 'pdf', '8713137', '0', null), ('ff80808139b3345d0139b3f0fc0b0420', 'd10b79877c22663d5fe1fe4065e8ee08', null, '2012-09-11 14:07:58', 'group1/M00/00/01/wKgoM1BO1UuTpBpqAAMbpyFyjQE638.pdf', 'RAND报告 - 英文.pdf', 'pdf', '203687', '0', null), ('ff80808139b3345d0139b36b2a490136', '366d90e2e26470a8d6ff5de09499919f', null, '2012-09-11 11:41:48', 'group1/M00/00/01/wKgoM1BOswnx5JWoAASz3i88_z4222.jpg', '小妞.jpg', 'jpg', '308190', '0', null), ('ff80808139b3345d0139b34bf8a8008b', '366d90e2e26470a8d6ff5de09499919f', null, '2012-09-11 11:07:44', 'group1/M00/00/01/wKgoM1BOqwzRUxJTAASz3i88_z4951.jpg', '小妞.jpg', 'jpg', '308190', '0', null), ('ff80808139b3345d0139b34bab6a008a', '366d90e2e26470a8d6ff5de09499919f', null, '2012-09-11 11:07:24', 'group1/M00/00/01/wKgoM1BOqvmxKgPOAASz3i88_z4155.jpg', '小妞.jpg', 'jpg', '308190', '0', null), ('8aa5018239b2ffa80139b30885010032', '5de47bfa8fb4b7c3414dae0dff9c411e', null, '2012-09-11 09:54:03', 'group1/M00/00/01/wKgoMlBOmbLitoW0ALabe753MUw827.rar', '20120717改动效果和切图说明.rar', 'rar', '11967355', '0', null), ('3f400e0a-8b67-4517-894a-0e300049b56c', '9d377b10ce778c4938b3c7e2c63a229a', null, '2012-09-10 16:56:11', 'group1/M00/00/01/wKgoM1BNqymj_SbgAAvea_OGt2M851.jpg', 'viva-fdfsPenguins.jpg', 'jpg', '777835', '0', null);
COMMIT;

-- ----------------------------
--  Table structure for `tbgroup`
-- ----------------------------
DROP TABLE IF EXISTS `tbgroup`;
CREATE TABLE `tbgroup` (
  `id` varchar(255) NOT NULL,
  `activeCount` int(11) NOT NULL,
  `created` datetime DEFAULT NULL,
  `currentTrunkFileId` int(11) NOT NULL,
  `currentWriteServer` int(11) NOT NULL,
  `freeMB` bigint(20) NOT NULL,
  `groupName` varchar(255) DEFAULT NULL,
  `storageCount` int(11) NOT NULL,
  `storageHttpPort` int(11) NOT NULL,
  `storagePort` int(11) NOT NULL,
  `storePathCount` int(11) NOT NULL,
  `subdirCountPerPath` int(11) NOT NULL,
  `trunkFreeMB` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tbgroupday`
-- ----------------------------
DROP TABLE IF EXISTS `tbgroupday`;
CREATE TABLE `tbgroupday` (
  `id` varchar(255) NOT NULL,
  `activeCount` int(11) NOT NULL,
  `created` datetime DEFAULT NULL,
  `currentTrunkFileId` int(11) NOT NULL,
  `currentWriteServer` int(11) NOT NULL,
  `freeMB` bigint(20) NOT NULL,
  `groupName` varchar(255) DEFAULT NULL,
  `storageCount` int(11) NOT NULL,
  `storageHttpPort` int(11) NOT NULL,
  `storagePort` int(11) NOT NULL,
  `storePathCount` int(11) NOT NULL,
  `subdirCountPerPath` int(11) NOT NULL,
  `trunkFreeMB` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tbgrouphour`
-- ----------------------------
DROP TABLE IF EXISTS `tbgrouphour`;
CREATE TABLE `tbgrouphour` (
  `id` varchar(255) NOT NULL,
  `activeCount` int(11) NOT NULL,
  `created` datetime DEFAULT NULL,
  `currentTrunkFileId` int(11) NOT NULL,
  `currentWriteServer` int(11) NOT NULL,
  `freeMB` bigint(20) NOT NULL,
  `groupName` varchar(255) DEFAULT NULL,
  `storageCount` int(11) NOT NULL,
  `storageHttpPort` int(11) NOT NULL,
  `storagePort` int(11) NOT NULL,
  `storePathCount` int(11) NOT NULL,
  `subdirCountPerPath` int(11) NOT NULL,
  `trunkFreeMB` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tbstorage`
-- ----------------------------
DROP TABLE IF EXISTS `tbstorage`;
CREATE TABLE `tbstorage` (
  `id` varchar(255) NOT NULL,
  `cpu` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `curStatus` varchar(255) DEFAULT NULL,
  `currentWritePath` int(11) NOT NULL,
  `domainName` varchar(255) DEFAULT NULL,
  `freeMB` bigint(20) NOT NULL,
  `groupName` varchar(255) DEFAULT NULL,
  `ifTrunkServer` tinyint(1) NOT NULL,
  `ipAddr` varchar(255) DEFAULT NULL,
  `joinTime` datetime DEFAULT NULL,
  `lastHeartBeatTime` datetime DEFAULT NULL,
  `lastSourceUpdate` datetime DEFAULT NULL,
  `lastSyncUpdate` datetime DEFAULT NULL,
  `lastSyncedTimestamp` datetime DEFAULT NULL,
  `mem` float NOT NULL,
  `srcIpAddr` varchar(255) DEFAULT NULL,
  `storageHttpPort` int(11) NOT NULL,
  `storagePort` int(11) NOT NULL,
  `storePathCount` int(11) NOT NULL,
  `subdirCountPerPath` int(11) NOT NULL,
  `successAppendBytes` bigint(20) NOT NULL,
  `successAppendCount` bigint(20) NOT NULL,
  `successCreateLinkCount` bigint(20) NOT NULL,
  `successDeleteCount` bigint(20) NOT NULL,
  `successDeleteLinkCount` bigint(20) NOT NULL,
  `successDownloadCount` bigint(20) NOT NULL,
  `successDownloadloadBytes` bigint(20) NOT NULL,
  `successFileOpenCount` bigint(20) NOT NULL,
  `successFileReadCount` bigint(20) NOT NULL,
  `successFileWriteCount` bigint(20) NOT NULL,
  `successGetMetaCount` bigint(20) NOT NULL,
  `successModifyBytes` bigint(20) NOT NULL,
  `successModifyCount` bigint(20) NOT NULL,
  `successSetMetaCount` bigint(20) NOT NULL,
  `successSyncInBytes` bigint(20) NOT NULL,
  `successSyncOutBytes` bigint(20) NOT NULL,
  `successTruncateCount` bigint(20) NOT NULL,
  `successUploadBytes` bigint(20) NOT NULL,
  `successUploadCount` bigint(20) NOT NULL,
  `totalAppendBytes` bigint(20) NOT NULL,
  `totalAppendCount` bigint(20) NOT NULL,
  `totalCreateLinkCount` bigint(20) NOT NULL,
  `totalDeleteCount` bigint(20) NOT NULL,
  `totalDeleteLinkCount` bigint(20) NOT NULL,
  `totalDownloadCount` bigint(20) NOT NULL,
  `totalDownloadloadBytes` bigint(20) NOT NULL,
  `totalFileOpenCount` bigint(20) NOT NULL,
  `totalFileReadCount` bigint(20) NOT NULL,
  `totalFileWriteCount` bigint(20) NOT NULL,
  `totalGetMetaCount` bigint(20) NOT NULL,
  `totalMB` bigint(20) NOT NULL,
  `totalModifyBytes` bigint(20) NOT NULL,
  `totalModifyCount` bigint(20) NOT NULL,
  `totalSetMetaCount` bigint(20) NOT NULL,
  `totalSyncInBytes` bigint(20) NOT NULL,
  `totalSyncOutBytes` bigint(20) NOT NULL,
  `totalTruncateCount` bigint(20) NOT NULL,
  `totalUploadBytes` bigint(20) NOT NULL,
  `totalUploadCount` bigint(20) NOT NULL,
  `upTime` datetime DEFAULT NULL,
  `uploadPriority` int(11) NOT NULL,
  `version` varchar(255) DEFAULT NULL,
  `group_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK82E5BC4D222966B3` (`group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tbstorageday`
-- ----------------------------
DROP TABLE IF EXISTS `tbstorageday`;
CREATE TABLE `tbstorageday` (
  `id` varchar(255) NOT NULL,
  `cpu` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `curStatus` varchar(255) DEFAULT NULL,
  `currentWritePath` int(11) NOT NULL,
  `domainName` varchar(255) DEFAULT NULL,
  `freeMB` bigint(20) NOT NULL,
  `groupName` varchar(255) DEFAULT NULL,
  `ifTrunkServer` tinyint(1) NOT NULL,
  `ipAddr` varchar(255) DEFAULT NULL,
  `joinTime` datetime DEFAULT NULL,
  `lastHeartBeatTime` datetime DEFAULT NULL,
  `lastSourceUpdate` datetime DEFAULT NULL,
  `lastSyncUpdate` datetime DEFAULT NULL,
  `lastSyncedTimestamp` datetime DEFAULT NULL,
  `mem` float NOT NULL,
  `srcIpAddr` varchar(255) DEFAULT NULL,
  `storageHttpPort` int(11) NOT NULL,
  `storagePort` int(11) NOT NULL,
  `storePathCount` int(11) NOT NULL,
  `subdirCountPerPath` int(11) NOT NULL,
  `successAppendBytes` bigint(20) NOT NULL,
  `successAppendCount` bigint(20) NOT NULL,
  `successCreateLinkCount` bigint(20) NOT NULL,
  `successDeleteCount` bigint(20) NOT NULL,
  `successDeleteLinkCount` bigint(20) NOT NULL,
  `successDownloadCount` bigint(20) NOT NULL,
  `successDownloadloadBytes` bigint(20) NOT NULL,
  `successFileOpenCount` bigint(20) NOT NULL,
  `successFileReadCount` bigint(20) NOT NULL,
  `successFileWriteCount` bigint(20) NOT NULL,
  `successGetMetaCount` bigint(20) NOT NULL,
  `successModifyBytes` bigint(20) NOT NULL,
  `successModifyCount` bigint(20) NOT NULL,
  `successSetMetaCount` bigint(20) NOT NULL,
  `successSyncInBytes` bigint(20) NOT NULL,
  `successSyncOutBytes` bigint(20) NOT NULL,
  `successTruncateCount` bigint(20) NOT NULL,
  `successUploadBytes` bigint(20) NOT NULL,
  `successUploadCount` bigint(20) NOT NULL,
  `totalAppendBytes` bigint(20) NOT NULL,
  `totalAppendCount` bigint(20) NOT NULL,
  `totalCreateLinkCount` bigint(20) NOT NULL,
  `totalDeleteCount` bigint(20) NOT NULL,
  `totalDeleteLinkCount` bigint(20) NOT NULL,
  `totalDownloadCount` bigint(20) NOT NULL,
  `totalDownloadloadBytes` bigint(20) NOT NULL,
  `totalFileOpenCount` bigint(20) NOT NULL,
  `totalFileReadCount` bigint(20) NOT NULL,
  `totalFileWriteCount` bigint(20) NOT NULL,
  `totalGetMetaCount` bigint(20) NOT NULL,
  `totalMB` bigint(20) NOT NULL,
  `totalModifyBytes` bigint(20) NOT NULL,
  `totalModifyCount` bigint(20) NOT NULL,
  `totalSetMetaCount` bigint(20) NOT NULL,
  `totalSyncInBytes` bigint(20) NOT NULL,
  `totalSyncOutBytes` bigint(20) NOT NULL,
  `totalTruncateCount` bigint(20) NOT NULL,
  `totalUploadBytes` bigint(20) NOT NULL,
  `totalUploadCount` bigint(20) NOT NULL,
  `upTime` datetime DEFAULT NULL,
  `uploadPriority` int(11) NOT NULL,
  `version` varchar(255) DEFAULT NULL,
  `group_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAC95482F4CFC637F` (`group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tbstoragehour`
-- ----------------------------
DROP TABLE IF EXISTS `tbstoragehour`;
CREATE TABLE `tbstoragehour` (
  `id` varchar(255) NOT NULL,
  `cpu` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `curStatus` varchar(255) DEFAULT NULL,
  `currentWritePath` int(11) NOT NULL,
  `domainName` varchar(255) DEFAULT NULL,
  `freeMB` bigint(20) NOT NULL,
  `groupName` varchar(255) DEFAULT NULL,
  `ifTrunkServer` tinyint(1) NOT NULL,
  `ipAddr` varchar(255) DEFAULT NULL,
  `joinTime` datetime DEFAULT NULL,
  `lastHeartBeatTime` datetime DEFAULT NULL,
  `lastSourceUpdate` datetime DEFAULT NULL,
  `lastSyncUpdate` datetime DEFAULT NULL,
  `lastSyncedTimestamp` datetime DEFAULT NULL,
  `mem` float NOT NULL,
  `srcIpAddr` varchar(255) DEFAULT NULL,
  `storageHttpPort` int(11) NOT NULL,
  `storagePort` int(11) NOT NULL,
  `storePathCount` int(11) NOT NULL,
  `subdirCountPerPath` int(11) NOT NULL,
  `successAppendBytes` bigint(20) NOT NULL,
  `successAppendCount` bigint(20) NOT NULL,
  `successCreateLinkCount` bigint(20) NOT NULL,
  `successDeleteCount` bigint(20) NOT NULL,
  `successDeleteLinkCount` bigint(20) NOT NULL,
  `successDownloadCount` bigint(20) NOT NULL,
  `successDownloadloadBytes` bigint(20) NOT NULL,
  `successFileOpenCount` bigint(20) NOT NULL,
  `successFileReadCount` bigint(20) NOT NULL,
  `successFileWriteCount` bigint(20) NOT NULL,
  `successGetMetaCount` bigint(20) NOT NULL,
  `successModifyBytes` bigint(20) NOT NULL,
  `successModifyCount` bigint(20) NOT NULL,
  `successSetMetaCount` bigint(20) NOT NULL,
  `successSyncInBytes` bigint(20) NOT NULL,
  `successSyncOutBytes` bigint(20) NOT NULL,
  `successTruncateCount` bigint(20) NOT NULL,
  `successUploadBytes` bigint(20) NOT NULL,
  `successUploadCount` bigint(20) NOT NULL,
  `totalAppendBytes` bigint(20) NOT NULL,
  `totalAppendCount` bigint(20) NOT NULL,
  `totalCreateLinkCount` bigint(20) NOT NULL,
  `totalDeleteCount` bigint(20) NOT NULL,
  `totalDeleteLinkCount` bigint(20) NOT NULL,
  `totalDownloadCount` bigint(20) NOT NULL,
  `totalDownloadloadBytes` bigint(20) NOT NULL,
  `totalFileOpenCount` bigint(20) NOT NULL,
  `totalFileReadCount` bigint(20) NOT NULL,
  `totalFileWriteCount` bigint(20) NOT NULL,
  `totalGetMetaCount` bigint(20) NOT NULL,
  `totalMB` bigint(20) NOT NULL,
  `totalModifyBytes` bigint(20) NOT NULL,
  `totalModifyCount` bigint(20) NOT NULL,
  `totalSetMetaCount` bigint(20) NOT NULL,
  `totalSyncInBytes` bigint(20) NOT NULL,
  `totalSyncOutBytes` bigint(20) NOT NULL,
  `totalTruncateCount` bigint(20) NOT NULL,
  `totalUploadBytes` bigint(20) NOT NULL,
  `totalUploadCount` bigint(20) NOT NULL,
  `upTime` datetime DEFAULT NULL,
  `uploadPriority` int(11) NOT NULL,
  `version` varchar(255) DEFAULT NULL,
  `group_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE615C3B1C92024B7` (`group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tbuser`
-- ----------------------------
DROP TABLE IF EXISTS `tbuser`;
CREATE TABLE `tbuser` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `power` varchar(255) DEFAULT NULL,
  `psword` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `tbuser`
-- ----------------------------
BEGIN;
INSERT INTO `tbuser` VALUES ('4028a809398b076901398b0d0b070020', 'admin', '2', '123456'), ('4028a80939beb9000139bebebaeb001e', '123456', '1', '123456');
COMMIT;

-- ----------------------------
--  Table structure for `tbwarningdata`
-- ----------------------------
DROP TABLE IF EXISTS `tbwarningdata`;
CREATE TABLE `tbwarningdata` (
  `id` varchar(255) NOT NULL,
  `wdCpu` varchar(255) DEFAULT NULL,
  `wdFreeMB` bigint(20) NOT NULL,
  `wdGroupName` varchar(255) DEFAULT NULL,
  `wdIpAddr` varchar(255) DEFAULT NULL,
  `wdMem` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `tbwarningdata`
-- ----------------------------
BEGIN;
INSERT INTO `tbwarningdata` VALUES ('4028a8e7399e94eb01399e9897cd0015', '3', '5', null, '192.168.40.53', '3'), ('4028a8e7399e94eb01399e98706f0014', '3', '45450', null, '192.168.40.51', '3');
COMMIT;

-- ----------------------------
--  Table structure for `tbwarninguser`
-- ----------------------------
DROP TABLE IF EXISTS `tbwarninguser`;
CREATE TABLE `tbwarninguser` (
  `id` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `tbwarninguser`
-- ----------------------------
BEGIN;
INSERT INTO `tbwarninguser` VALUES ('ff80808139b3345d0139b405791e048f', 'jing.zhang@vivame.cn', '张静', '999999999999999999999999');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
