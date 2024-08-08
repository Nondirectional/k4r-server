import 'dart:io';

import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import 'package:k4r_client/component/api_caller.dart';
import 'package:mime/mime.dart';
import 'package:path/path.dart' as path;

class ProfilePage extends StatefulWidget {
  const ProfilePage({super.key});

  @override
  ProfilePageState createState() => ProfilePageState();
}

class ProfilePageState extends State<ProfilePage> {
  File? _image = File("C:/data/files/avatar/42313281719000119.jpg");
  final _nicknameController = TextEditingController();
  final _emailController = TextEditingController();

  Future<void> _pickImage() async {
    final pickedFile =
        await ImagePicker().pickImage(source: ImageSource.gallery);
    if (pickedFile != null) {
      // 获取文件扩展名
      String extension = path.extension(pickedFile.path).toLowerCase();
      String? mimeType = lookupMimeType(extension);
      if (mimeType == null) {
        throw Exception('未知的媒体类型');
      }
      setState(() {
        _image = File(pickedFile.path);
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('编辑个人资料'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: <Widget>[
            GestureDetector(
              onTap: _pickImage,
              child: CircleAvatar(
                radius: 50,
                backgroundImage: _image != null ? FileImage(_image!) : null,
                child: _image == null
                    ? const Icon(Icons.add_a_photo, size: 50)
                    : null,
              ),
            ),
            const SizedBox(height: 20),
            TextField(
              controller: _nicknameController,
              decoration: const InputDecoration(
                labelText: '昵称',
              ),
            ),
            const SizedBox(height: 20),
            TextField(
              controller: _emailController,
              decoration: const InputDecoration(
                labelText: '邮箱',
              ),
            ),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: () async {
                // 处理保存逻辑
                Map<String, dynamic> map = {};
                if (_nicknameController.text.isNotEmpty) {
                  map["nickname"] = _nicknameController.text;
                }
                if (_emailController.text.isNotEmpty) {
                  map["email"] = _emailController.text;
                }
                if (_image != null) {
                  String extension = path.extension(_image!.path).toLowerCase();
                  String? mimeType = lookupMimeType(extension);
                  DioMediaType mediaType = DioMediaType.parse(mimeType!);
                  MultipartFile avatar = await MultipartFile.fromFile(
                      _image!.path,
                      filename: path.basename(_image!.path),
                      contentType: mediaType);
                  map["avatar"] = avatar;
                }
                ApiCaller apiCaller = ApiCaller(context);
                Response? response = await apiCaller.updateProfile(FormData.fromMap(map));
              },
              child: const Text('保存'),
            ),
          ],
        ),
      ),
    );
  }
}
