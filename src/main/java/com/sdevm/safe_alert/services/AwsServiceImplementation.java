package com.sdevm.safe_alert.services;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AwsServiceImplementation implements AwsService {

    @Autowired
    private S3Client s3Client;

    // Method to upload a file to an S3 bucket
    @Override
    public void uploadFile(
            final String bucketName,
            final String keyName,
            final Long contentLength,
            final String contentType,
            final InputStream value) {
        s3Client.putObject(builder -> builder.bucket(bucketName).key(keyName)
                .contentLength(contentLength).contentType(contentType),
                RequestBody.fromInputStream(value, contentLength));
        log.info("File uploaded to bucket({}): {}", bucketName, keyName);
    }

    // Method to download a file from an S3 bucket
    @Override
    public ByteArrayOutputStream downloadFile(
            final String bucketName,
            final String keyName) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .build();
        InputStream inputStream = s3Client.getObject(getObjectRequest, ResponseTransformer.toInputStream());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        int len;
        byte[] buffer = new byte[4096];
        try {
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
                outputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("File downloaded from bucket({}): {}", bucketName, keyName);
        return outputStream;
    }

    // Method to list files in an S3 bucket
    @Override
    public List<String> listFiles(final String bucketName) {
        List<String> keys = new ArrayList<>();
        ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .build();

        ListObjectsV2Response listObjectsResponse;
        do {
            listObjectsResponse = s3Client.listObjectsV2(listObjectsRequest);

            listObjectsResponse.contents().stream()
                    .filter(item -> !item.key().endsWith("/"))
                    .map(S3Object::key)
                    .forEach(keys::add);

            listObjectsRequest = listObjectsRequest.toBuilder()
                    .continuationToken(listObjectsResponse.nextContinuationToken())
                    .build();
        } while (listObjectsResponse.isTruncated());

        log.info("Files found in bucket({}): {}", bucketName, keys);
        return keys;
    }

    // Method to delete a file from an S3 bucket
    @Override
    public void deleteFile(
            final String bucketName,
            final String keyName) {
        s3Client.deleteObject(builder -> builder.bucket(bucketName).key(keyName).build());
        log.info("File deleted from bucket({}): {}", bucketName, keyName);
    }
}
