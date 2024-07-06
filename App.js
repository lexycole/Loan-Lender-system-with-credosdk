/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

 import React from 'react';
 import type {Node} from 'react';
 
 import {
   SafeAreaView,
   ScrollView,
   TextInput,
   StyleSheet,
   Button,
   Text
 } from 'react-native';

  import { NativeModules } from 'react-native';

 const App: () => Node = () => {
   const [url, setUrl] = React.useState("https://**.credolab.com");
   const [auth, setAuth] = React.useState("");
   const [referenceNumber, setRn] = React.useState("");
   const [resultText, setResultText] = React.useState("");
collect = async () => {
  try { 
      await NativeModules.CredoAppModule.execute(url, auth, referenceNumber);
      setResultText("Upload successful. Ref number " + referenceNumber)
      console.log("Upload successful");
  }
  catch (e) {
      console.log(e.code + " " + e);
      setResultText("Upload failed. " + e.code + " " + e)
  }
 
 }
 
   return (
     <SafeAreaView style={styles.body}>
         <ScrollView>
           <TextInput
             style={styles.input}
             value={url}
             onChangeText={(url) => setUrl(url)}
           />
           <TextInput
             style={styles.input}
             value={auth}
             onChangeText={(auth) => setAuth(auth)}
           />
          <TextInput
             style={styles.input}
             value={referenceNumber}
             onChangeText={(rn) => setRn(rn)}
           />
           <Button title="Collect" onPress={this.collect} style={styles.btn}/>
           <Text style={styles.resultText}>{resultText}</Text>
       </ScrollView>
     </SafeAreaView>
   );
 };
 
 const styles = StyleSheet.create({
  body: {
    flex: 1,
    backgroundColor: '#bebebe'
   },
  input: {
    height: 40,
    margin: 12,
    borderWidth: 1,
    borderColor: '#FFFFFF',
    padding: 10,
  },
  btn: {
    backgroundColor: '#1689d2', 
    color: '#FFFFFF'
  },
  resultText: {
    fontSize: 20,
    margin: 20,
    color: '#FFFFFF' 
  }
 });
 
 export default App;
