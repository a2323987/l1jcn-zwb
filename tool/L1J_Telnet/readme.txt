####################
 L1J-JP Telnet Tool
####################

■动作环境
PHPとMySQLが动作するWebサーバー
php.iniのdefault_charsetはShift_JISでなければいけません。（default_charset = "Shift_JIS"）
※外部からのポート23が闭じている事。

■インストールと设定
1.server.propertiesの变更
  TelnetServer = True
2.ダウンロードしたファイルを解冻しWebサーバーのフォルダにコピーします。
3.setup.phpを环境に合わせて变更します。
4.ブラウザでindex.phpにアクセスしてログインします。

■コマンドの入力
ゲーム内と同じように先头に付加する文字列で动作が变わります。
先头の文字列が一致しない场合は何も行いません。

【.】
acountsテーブルのaccess_levelが200の场合にTelnetコマンドを实行出来ます。
结果は“Result > ”に表示されます。
ただしglobalchatコマンドは实行出来ません。
例：
.playerid [キャラクター名]
.charstatus [objid]

【&】
全体チャットで发言出来ます。
charactersテーブルのIsGMが200だと名前が[******]となります。
例：
&こんにちは
/////google/////
■操作环境
PHP和MySQL的Web服务器运行
default_charset在php.ini中没有除非Shift_JIS 。 （ Default_charset = “ Shift_JIS ” ）
※外部端口23可以被关闭。

■安装和配置
变更的1.server.properties
   TelnetServer =真
2 。解压下载的文件文件夹中的Web服务器。
变更3.setup.php您的特定环境。
4 。在您的浏览器来登录访问的index.php 。

■输入命令
WARIMASU变化字符串附加到运作的方式同游戏内。
可能不匹配的第一个字符串没有采取任何行动。

[ 。 ]
表200 acounts access_level来樱花如果Telnet命令行实。
其结果是“结果” “显示。
然而globalchat命令行来MASEN实。
例如：
。 playerid [字符名称]
。 charstatus [ objid ]

[ ＆ ]
来樱花发言在整个聊天。
IsGM表的字符为200 [******]名称。
例如：
＆喜