# SSL Pinning Demo Application

This repository contains a demonstration Android application that showcases SSL pinning using the Charles Proxy tool. SSL pinning is a security measure that enhances the security of network connections by ensuring that the app only communicates with servers that possess the expected SSL certificate. This helps protect against man-in-the-middle attacks and unauthorized server impersonation.

SSL pinning, also known as certificate pinning, is a security technique used to prevent man-in-the-middle attacks. SSL pinning can provide an extra layer of security, especially for high-value applications such as financial services or sensitive communications, where the risk of a man-in-the-middle attack is higher.

- What is MITM (Man-in-the-Middle)

<img src="/screenshots/mitm.jpg"/>

A man-in-the-middle (MITM) attack is a type of cyber attack where an attacker intercepts the communication between two parties who believe they are communicating directly with each other. The attacker can then eavesdrop on the communication or alter it to their advantage.


## 1. Types of SSL Pining

- Certificate Pinning

        1. Trust Manager

        2. Network security configuration ( minSdk 24 (Nougat) )

-  Public Key Pinning

        1. Network security configuration ( minSdk 24 (Nougat) )

        2. OKHTTP with certificate pinning

        3. Retrofit with custom OKHTTP

## 2. SSL Certificate Chain

        1. Root Certificate

        2. Intermediate Certificates

        3. End User Certificate

| [Certificate Chain](https://youtu.be/msBrdFiSvW4)                                                                       |                                                                                                                         |
|-------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------|
| <img src="https://github.com/chichi289/SSL-Pinning/blob/master/screenshots/1.png" width="400" hspace="10" vspace="10"/> | <img src="https://github.com/chichi289/SSL-Pinning/blob/master/screenshots/2.png" width="400" hspace="10" vspace="10"/> |
| <img src="https://github.com/chichi289/SSL-Pinning/blob/master/screenshots/3.png" width="400" hspace="10" vspace="10"/> | <img src="https://github.com/chichi289/SSL-Pinning/blob/master/screenshots/4.png" width="400" hspace="10" vspace="10"/> |

### API Endpoints Used for SSL Pinning:

- api.github.com:

- jsonplaceholder.typicode.com:

### Charles Proxy SSL Proxying:

Charles Proxy is used as a debugging tool to intercept and inspect network traffic between the Android application and the remote servers. In this demo, Charles Proxy is set up to perform SSL proxying for the two API endpoints mentioned above: api.github.com and jsonplaceholder.typicode.com.

### Why we need intermediate certificate? Root certificate is not enough?
- Security: By using an intermediate certificate, the root CA can limit the damage if the intermediate certificate is compromised. Since the intermediate certificate is signed by the root CA, it can be revoked without affecting the root CA's ability to issue new certificates.

- Flexibility: By using an intermediate certificate, the root CA can delegate certificate issuance to a third party without giving them access to the root CA's private key. This allows for more flexibility in managing the certificate infrastructure.

- Efficiency: By using an intermediate certificate, the root CA can issue a new intermediate certificate without needing to update all the entities' certificates that were signed by the previous intermediate certificate. This can save time and effort in managing the certificate infrastructure.

- So, while root certificates are the foundation of trust in digital certificates, intermediate certificates provide additional security, flexibility, and efficiency benefits that can make them a valuable part of the certificate infrastructure.
- 
### "The root certificate is already trusted by your browser" How?

- When you install a web browser or operating system, it typically comes preloaded with a list of trusted root certificates, known as a "root store". These root certificates are issued by well-known Certificate Authorities (CAs), such as DigiCert, VeriSign, and GlobalSign.

- These trusted root certificates are preinstalled because they have been audited and vetted by the browser or operating system vendor, and are considered to be highly trusted. When your browser encounters a certificate chain that ends in a root certificate that it already trusts, it will accept that certificate as valid, and establish a secure connection with the server.

- This initial trust in the root certificate is essential to the security of the SSL/TLS protocol. If the browser did not trust any root certificates, it would not be able to establish secure connections with any websites or online services. By trusting a set of well-known root certificates, the browser can establish secure connections with a wide variety of websites and online services, and users can trust that their data is being transmitted securely and privately.

### What if after 2 years new certificate authorities company starts issuing a certificate?

- If a new certificate authority (CA) is established and starts issuing certificates, it would need to go through a process to become trusted by browsers and operating systems. This process typically involves audits and other forms of verification to establish the trustworthiness of the CA.

- Once a new CA is trusted, its root certificate would need to be added to the root store of browsers and operating systems. This can happen through software updates that are pushed out to devices, or through browser or operating system updates.

- In the meantime, any SSL/TLS certificates issued by the new CA would not be trusted by default on devices that have not yet updated their root store. However, website owners can choose to include the new CA's intermediate certificate in their certificate chain, which can help establish trust for users who have not yet updated their root store.

- It's worth noting that the process of adding a new CA to the root store can take some time, and it's important for users and website owners to stay aware of any updates or changes to the trusted root store on their devices.

### What if users do not update OS or browser?

- By including the intermediate certificate in the chain, website owners can help establish trust for users who have not yet updated their root store. This can be especially important in situations where the website is operated by an organization that is closely associated with the new CA, or where the website is likely to be visited by users who have not yet updated their root store.

### However, website owners can choose to include the new CA's intermediate certificate in their certificate chain, which can help establish trust for users who have not yet updated their root store." How?

- Website owners can choose to include the new CA's intermediate certificate in their certificate chain by obtaining a certificate from the new CA that includes the intermediate certificate. When a user's browser encounters the certificate chain, it will verify the certificates in the chain, starting with the SSL/TLS certificate presented by the website.

- If the user's browser does not yet trust the new CA's root certificate, it will check for the presence of the new CA's intermediate certificate in the certificate chain. If the intermediate certificate is present and is signed by a root certificate that the browser already trusts, then the browser will trust the SSL/TLS certificate presented by the website.

- By including the intermediate certificate in the chain, website owners can help establish trust for users who have not yet updated their root store. This can be especially important in situations where the website is operated by an organization that is closely associated with the new CA, or where the website is likely to be visited by users who have not yet updated their root store.

- However, it's worth noting that including an intermediate certificate in the certificate chain can also increase the complexity of certificate management for website owners. Additionally, website owners should exercise caution when including intermediate certificates from third-party CAs, as this could potentially open up security risks.

## 🔗 Links
[Generate public key certificate](https://medium.com/@jinal010990/generate-public-key-certificate-for-ssl-pinning-4400521c367b)

[Make Android apps secure with SSL pinning](https://medium.com/@jinal010990/make-android-apps-secure-with-ssl-pinning-16abb6f24421)

[Export & Download — SSL Certificate from Server (Site URL)](https://medium.com/@menakajain/export-download-ssl-certificate-from-server-site-url-bcfc41ea46a2)

[3 Ways How To Implement Certificate Pinning on Android](https://www.netguru.com/blog/3-ways-how-to-implement-certificate-pinning-on-android)

[Pin There, Done That!](https://medium.com/@click4abhishekagarwal/pin-there-done-that-93033a351354)

[SSL Server Test](https://www.ssllabs.com/ssltest)

[RSA 768 Cracking time](https://en.wikipedia.org/wiki/RSA_numbers#RSA-768)

[Online char count](https://www.charactercountonline.com/)

[Bit length calculator](https://planetcalc.com/8985/)