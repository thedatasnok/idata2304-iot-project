# Instructions on how to release a new version of the project

In this project, we use GitHub Actions to manage releases, but they need to be triggered.

The entire project is versioned as one - meaning a new version of ingest will also result in a new version of the sensor application being published.

## Steps

1. Set the version in [gradle.properties](../gradle.properties) to the version to release.

2. Set the version in [package.json](../apps/frontend/package.json) for the frontend application.

3. Commit the changes, with a message following the format: `build: bump versions for release`

4. Tag the commit with a tag, following the format:
  
    `v${major}.${minor}.${patch}`, which can be: `v1.0.0` for instance.

    ```sh
    git tag -a v1.0.0 -m "Release v1.0.0 of antiboom"
    ```

5. Push the changes to GitHub, including the tags using: 

    ```sh
    git push
    git push --tags
    ```

Once this is done the [release workflow](../.github/workflows/release.yml) will be triggered, and publish a new release to GitHub. 


To install the application on a sensor node, you can run the following commands on the sensor node:

```bash
sudo su
bash <(curl -s https://raw.githubusercontent.com/thedatasnok/idata2304-iot-project/main/tools/install-sensor.sh)
```
