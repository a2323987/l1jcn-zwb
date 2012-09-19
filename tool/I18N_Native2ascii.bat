title L1J-TW 多国语系
@echo off
goto Manu

::选单
:Manu
echo L1J-TW 多国语系 Internationalization
echo ┌─────────────────┐
echo │           多国语系转换　　　　　 │
echo └─────────────────┘
echo ---------------------------------------
echo (1) 转换 zh_TW
echo (2) 转换 jp_JP
echo (3) 转换 zh_TW + 转换 jp_JP
echo (4) 离开
echo ---------------------------------------
echo 请选择:
set /p num=执行:
echo 选择了%num%
goto go%num%




::============================以下是资料处理================================

::转换 繁体中文
::●请注意路径●●请修改成自己的Jdk/bin底下的native2ascii.exe路径●
::zh_TW
:go1
"C:\Program Files\Java\jdk1.6.0_23\bin\native2ascii.exe" -encoding UTF-8 ../data/language/messages_zh_TW.txt ../data/language/messages_zh_TW.properties
goto Manu



::转换 日文
::●请注意路径●●请修改成自己的java/bin底下的native2ascii.exe路径●
::jp_JP
:go2
"C:\Program Files\Java\jdk1.6.0_23\bin\native2ascii.exe" -encoding UTF-8 ../data/language/messages_ja_JP.txt ../data/language/messages_ja_JP.properties
goto Manu

::转换 繁体中文 + 日文
::●请注意路径●●请修改成自己的java/bin底下的native2ascii.exe路径●
::zh_TW + jp_JP
:go3
"C:\Program Files\Java\jdk1.6.0_23\bin\native2ascii.exe" -encoding UTF-8 ../data/language/messages_zh_TW.txt ../data/language/messages_zh_TW.properties
"C:\Program Files\Java\jdk1.6.0_23\bin\native2ascii.exe" -encoding UTF-8 ../data/language/messages_ja_JP.txt ../data/language/messages_ja_JP.properties
goto Manu

::离开
:go4
exit


