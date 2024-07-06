/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React from 'react';
import type {Node} from 'react';
import CredoAppService from 'credoapp-sdk';
import {
  SafeAreaView,
  ScrollView,
  TextInput,
  StyleSheet,
  Button,
  Text,
  Switch
} from 'react-native';

 import { NativeModules } from 'react-native';
import RNCredoappsdk from 'credoapp-sdk';

const App: () => Node = () => {

  const [url, setUrl] = React.useState("https://*.credolab.com");
  const [auth, setAuth] = React.useState("*");
  const [referenceNumber, setRn] = React.useState("");
  const [resultText, setResultText] = React.useState("");
  const [isEnabled, setIsEnabled] = React.useState(false);
  const toggleSwitch = () => setIsEnabled(previousState => !previousState);
collect = async () => {
 try { 
    
    setResultText("")

    var collectedData = await CredoAppService.execute(isEnabled)
    // var collectedData = await NativeModules.RNCredoappsdk.execute()
     var r = await login(url, auth)
     if(r.ok){
      var response = await r.json()

      r = await upload(url, response['access_token'], collectedData, referenceNumber)
      if(r.ok){
       setResultText("Upload successful. Ref number " + referenceNumber)
      }else {
       setResultText("Upload failed. " + JSON.stringify(await r.json()))
      }
     }else{
       setResultText("Upload failed. " + JSON.stringify(await r.json()))
     }
 }
 catch (e) {
     console.log(e.code + " " + e);
     setResultText("Upload failed. " + e.code + " " + e)
 }

}

const login = (url, auth) => {
return fetch(`${url}/api/account/v1/credoapplogin`, {
   method: 'POST',
   headers: {
     Accept: 'application/json',
     'Content-Type': 'application/json'
   },
   body: JSON.stringify({
     authKey: auth
   })
 })
}

const upload = (url, token, collectedData, rn) => {
 return fetch(`${url}/api/datasets/v1/upload`, {
    method: 'POST',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    },
    body: JSON.stringify({
      data: collectedData,
      referenceNumber: rn
    })
  })
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
            <Switch
              onValueChange={toggleSwitch}
              value={isEnabled}
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