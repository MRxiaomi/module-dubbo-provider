apiVersion: apps/v1
kind: Deployment
metadata:
  name: dubbo-provider
spec:
  replicas: 2
  selector:
    matchLabels:
      app: dubbo-provider
  strategy:
    type: RollingUpdate
  template:
    metadata:
      labels:
        app: dubbo-provider
    spec:
      containers:
      - name: dubbo-provider-service
        image: liuyumeng/dubbo-provider:<BUILD_TAG>
        ports:
        - containerPort: 8080