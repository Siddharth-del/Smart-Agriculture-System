import pandas as pd
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split
import joblib
import os

# Define paths relative to the script
base_path = os.path.dirname(__file__)
data_path = os.path.join(base_path, 'data', 'crop_data.csv')
model_dir = os.path.join(base_path, '..', '..', '..', '..', '..', 'resources', 'ml')

# 1. Load and Validate Data
try:
    df = pd.read_csv(data_path, on_bad_lines='skip')
    print(f"Loaded {len(df)} rows.")
    
    # Check if expected columns exist
    features = ['N', 'P', 'K', 'temperature', 'humidity', 'ph', 'rainfall']
    target = 'crop'
    
    X = df[features]
    y = df[target]

    # 2. Train Model
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)
    model = RandomForestClassifier(n_estimators=100)
    model.fit(X_train, y_train)

    # 3. Save for Spring Boot
    if not os.path.exists(model_dir):
        os.makedirs(model_dir)
    
    joblib.dump(model, os.path.join(model_dir, 'crop_model.pkl'))
    print("✅ Crop model trained and saved to Spring Boot resources!")

except Exception as e:
    print(f"- Error: {e}")