import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'color.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'AstraPay SDK',
      theme: ThemeData(
        primarySwatch: astraPayColor,
      ),
      home: MyHomePage(title: 'AstraPay SDK Demo'),
      debugShowCheckedModeBanner: false,
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  Future<void> _testOpenSdk(String type) async {
    const platform = const MethodChannel("channel_qris");
    try {
      if (type == 'qris') {
        final int result = await platform.invokeMethod('startQris');
        print(result);
      } else if (type == 'webviewtopup') {
        final int result = await platform.invokeMethod('startWebViewTopUp');
        print(result);
      }
    } on PlatformException catch (e) {
      print('${e.message}');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            ElevatedButton(
                onPressed: () => _testOpenSdk('qris'), child: Text('QRIS')),
            ElevatedButton(
                onPressed: () => _testOpenSdk('webviewtopup'),
                child: Text('WebView Top Up')),
          ],
        ),
      ),
    );
  }
}
